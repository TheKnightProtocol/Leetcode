class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private int maxSum = Integer.MIN_VALUE; // Stores the maximum path sum

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // Compute max path sum on left and right subtrees
        int leftMax = Math.max(dfs(node.left), 0);  // Ignore negative values
        int rightMax = Math.max(dfs(node.right), 0); 

        // Update the global maxSum: node + left path + right path
        maxSum = Math.max(maxSum, node.val + leftMax + rightMax);

        // Return max path sum including this node and one of its subtrees
        return node.val + Math.max(leftMax, rightMax);
    }
}
