class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    // 🔥 New Method: Deserialize a string into a LinkedList
    public static ListNode deserialize(String data) {
        if (data == null || data.length() < 2) return null;  // Handle empty cases

        // Remove brackets and split by comma
        data = data.substring(1, data.length() - 1);  
        String[] parts = data.split(",");

        // Edge case: If there's no value
        if (parts.length == 0 || parts[0].isEmpty()) return null;

        // Convert string values to integers and create linked list
        ListNode head = new ListNode(Integer.parseInt(parts[0].trim()));
        ListNode current = head;
        for (int i = 1; i < parts.length; i++) {
            current.next = new ListNode(Integer.parseInt(parts[i].trim()));
            current = current.next;
        }
        return head;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;  // Either the intersection node or null
    }

    public static void main(String[] args) {
        // 🔥 Use the new deserialize method to build linked lists
        ListNode headA = ListNode.deserialize("[4,1,8,4,5]");
        ListNode headB = ListNode.deserialize("[5,6,1,8,4,5]");

        Solution sol = new Solution();
        ListNode intersection = sol.getIntersectionNode(headA, headB);

        if (intersection != null) {
            System.out.println("Intersected at '" + intersection.val + "'");
        } else {
            System.out.println("No intersection");
        }
    }
}
