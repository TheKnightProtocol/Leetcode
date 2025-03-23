class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentNumber) {
        if (node == null) return 0;

        currentNumber = currentNumber * 10 + node.val;

        // If it's a leaf node, return the current number
        if (node.left == null && node.right == null) {
            return currentNumber;
        }

        // Recursively sum the left and right subtrees
        return dfs(node.left, currentNumber) + dfs(node.right, currentNumber);
    }
}
