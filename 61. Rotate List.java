class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        k = k % n;
        if (k == 0) return head;
        ListNode prev = head;
        for (int i = 0; i < n - k - 1; i++) prev = prev.next;
        ListNode newHead = prev.next;
        prev.next = null;
        tail.next = head;
        return newHead;
    }
}
