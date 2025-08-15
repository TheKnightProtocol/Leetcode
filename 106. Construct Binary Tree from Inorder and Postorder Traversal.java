class Solution {
    int postIndex;
    Map<Integer, Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = inMap.get(rootVal);
        root.right = build(postorder, inIndex + 1, inEnd);
        root.left = build(postorder, inStart, inIndex - 1);
        return root;
    }
}
