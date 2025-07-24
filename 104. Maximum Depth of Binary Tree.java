class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public int maxDepth(TreeNode root) {
        // If the tree is empty, depth = 0
        if (root == null) {
            return 0;
        }
        
        // Find depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Return the larger one + 1 (for current node)
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
