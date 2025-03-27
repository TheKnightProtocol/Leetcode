import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val); // Visit root
        preorderHelper(node.left, result); // Visit left
        preorderHelper(node.right, result); // Visit right
    }

    // Test Code
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1: root = [1,null,2,3]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        System.out.println(solution.preorderTraversal(root1)); // Output: [1,2,3]

        // Example 2: root = [1,2,3,4,5,null,8,null,null,6,7,9]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(8);
        root2.left.right.left = new TreeNode(6);
        root2.left.right.right = new TreeNode(7);
        root2.right.right.left = new TreeNode(9);
        System.out.println(solution.preorderTraversal(root2)); // Output: [1,2,4,5,6,7,3,8,9]

        // Example 3: root = []
        System.out.println(solution.preorderTraversal(null)); // Output: []

        // Example 4: root = [1]
        TreeNode root4 = new TreeNode(1);
        System.out.println(solution.preorderTraversal(root4)); // Output: [1]
    }
}
