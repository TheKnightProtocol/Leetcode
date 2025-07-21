/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode current = pre.next;
        ListNode nextNode = null;

        for (int i = 0; i < right - left; i++) {
            nextNode = current.next;
            current.next = nextNode.next;
            nextNode.next = pre.next;
            pre.next = nextNode;
        }

        return dummy.next;
    }
}
