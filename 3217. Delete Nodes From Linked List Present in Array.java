class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        boolean[] toRemove = new boolean[100001];
        for (int num : nums) {
            toRemove[num] = true;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        
        while (current.next != null) {
            if (toRemove[current.next.val]) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return dummy.next;
    }
}
