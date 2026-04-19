class BSTIterator {
private:
    stack<TreeNode*> st;
public:
    BSTIterator(TreeNode* root) {
        while (root) {
            st.push(root);
            root = root->left;
        }
    }
    
    int next() {
        TreeNode* node = st.top();
        st.pop();
        int val = node->val;
        node = node->right;
        while (node) {
            st.push(node);
            node = node->left;
        }
        return val;
    }
    
    bool hasNext() {
        return !st.empty();
    }
};
