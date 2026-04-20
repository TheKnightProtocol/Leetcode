class Solution {
public:
    TreeNode* balanceBST(TreeNode* root) {
        vector<int> nodes;
        inorder(root, nodes);
        return build(nodes, 0, nodes.size() - 1);
    }

private:
    void inorder(TreeNode* node, vector<int>& nodes) {
        if (!node) return;
        inorder(node->left, nodes);
        nodes.push_back(node->val);
        inorder(node->right, nodes);
    }

    TreeNode* build(const vector<int>& nodes, int start, int end) {
        if (start > end) return nullptr;
        int mid = start + (end - start) / 2;
        TreeNode* root = new TreeNode(nodes[mid]);
        root->left = build(nodes, start, mid - 1);
        root->right = build(nodes, mid + 1, end);
        return root;
    }
};
