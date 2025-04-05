class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;         // Step 1: Copy next node's value
        node.next = node.next.next;       // Step 2: Bypass the next node
    }
}
