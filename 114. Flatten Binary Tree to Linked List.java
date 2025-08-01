class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);    // Step 1: Flatten left subtree
        flatten(root.right);   // Step 2: Flatten right subtree

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;      // Step 3: Set left to null
        root.right = left;     // Step 4: Move left subtree to right

        // Step 5: Move to the end of new right and attach original right
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        current.right = right;
    }
}
