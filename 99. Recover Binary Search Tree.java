class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    TreeNode first, second, prev;

    public void recoverTree(TreeNode root) {
        prev = new TreeNode(Integer.MIN_VALUE);
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (first == null && prev.val >= node.val) {
            first = prev;
        }
        if (first != null && prev.val >= node.val) {
            second = node;
        }

        prev = node;

        inorder(node.right);
    }
}
