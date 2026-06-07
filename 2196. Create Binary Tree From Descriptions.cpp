#include <unordered_map>
#include <unordered_set>

class Solution {
public:
    TreeNode* createBinaryTree(vector<vector<int>>& descriptions) {
        unordered_map<int, TreeNode*> nodes;
        unordered_set<int> children;
        for (auto& desc : descriptions) {
            int parent = desc[0], child = desc[1], isLeft = desc[2];
            if (nodes.find(parent) == nodes.end())
                nodes[parent] = new TreeNode(parent);
            if (nodes.find(child) == nodes.end())
                nodes[child] = new TreeNode(child);
            if (isLeft)
                nodes[parent]->left = nodes[child];
            else
                nodes[parent]->right = nodes[child];
            children.insert(child);
        }
        for (auto& p : nodes) {
            if (children.find(p.first) == children.end())
                return p.second;
        }
        return nullptr;
    }
};
