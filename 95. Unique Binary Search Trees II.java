import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = buildTrees(start, i - 1);
            List<TreeNode> rightTrees = buildTrees(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    result.add(cloneTree(node));
                }
            }
        }

        return result;
    }

    private TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);
        return newNode;
    }

    public List<List<Integer>> serializeTrees(List<TreeNode> trees) {
        List<List<Integer>> result = new ArrayList<>();
        for (TreeNode root : trees) {
            result.add(serialize(root));
        }
        return result;
    }

    private List<Integer> serialize(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                result.add(null);
                continue;
            }
            result.add(curr.val);
            queue.add(curr.left);
            queue.add(curr.right);
        }

        int i = result.size() - 1;
        while (i >= 0 && result.get(i) == null) i--;
        return result.subList(0, i + 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 3;
        List<TreeNode> trees = s.generateTrees(n);
        List<List<Integer>> serialized = s.serializeTrees(trees);
        for (List<Integer> tree : serialized) {
            System.out.println(tree);
        }
    }
}
