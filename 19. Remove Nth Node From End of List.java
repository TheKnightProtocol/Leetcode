// This is the complete code you should submit for the problem.
// Do NOT include the ListNode class definition unless specifically told to by the platform.
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to simplify edge cases, especially when removing the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy;  // Fast pointer
        ListNode second = dummy; // Slow pointer

        // Move 'first' pointer n + 1 steps ahead of 'second'
        // This ensures 'second' will be at the node *before* the target node
        // when 'first' reaches the end.
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Move both pointers until 'first' reaches the end of the list (null)
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 'second.next' is the node to be removed.
        // Bypass it by linking 'second' to the node after 'second.next'.
        second.next = second.next.next;

        // The actual head of the modified list is dummy.next
        return dummy.next;
    }
}
