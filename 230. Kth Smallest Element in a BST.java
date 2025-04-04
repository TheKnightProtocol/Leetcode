class Solution {
    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode node, int k) {
        if (node == null) return;

        inOrder(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inOrder(node.right, k);
    }
}
