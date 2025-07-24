class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true; // Empty tree is symmetric
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // If both nodes are null, they mirror each other
        if (left == null && right == null) return true;
        
        // If one is null and the other isn't, they don't mirror
        if (left == null || right == null) return false;
        
        // Check if current nodes have the same value
        // Then recursively check left.left vs right.right AND left.right vs right.left
        return (left.val == right.val) 
            && isMirror(left.left, right.right) 
            && isMirror(left.right, right.left);
    }
}
