class Solution {
public:
    int sumEvenGrandparent(TreeNode* root) {
        return dfs(root, nullptr, nullptr);
    }

private:
    int dfs(TreeNode* node, TreeNode* parent, TreeNode* grandparent) {
        if (!node) return 0;

        int sum = 0;
        if (grandparent && grandparent->val % 2 == 0) {
            sum += node->val;
        }

        sum += dfs(node->left, node, parent);
        sum += dfs(node->right, node, parent);

        return sum;
    }
};
