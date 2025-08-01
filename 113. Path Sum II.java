import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        findPaths(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void findPaths(TreeNode node, int target, List<Integer> current, List<List<Integer>> result) {
        if (node == null) return;

        current.add(node.val);
        target -= node.val;

        // Check if it's a leaf and sum matches
        if (node.left == null && node.right == null && target == 0) {
            result.add(new ArrayList<>(current));  // Make a deep copy
        }

        findPaths(node.left, target, current, result);
        findPaths(node.right, target, current, result);

        current.remove(current.size() - 1); // Backtrack
    }
}
