class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Use the BST property to find LCA
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left; // both nodes are in left subtree
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right; // both nodes are in right subtree
            } else {
                return root; // this is the split point and hence the LCA
            }
        }
        return null;
    }
}
