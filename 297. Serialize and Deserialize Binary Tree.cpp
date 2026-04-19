#include <string>
#include <vector>
#include <queue>
#include <sstream>

using namespace std;

class Codec {
public:
    string serialize(TreeNode* root) {
        if (!root) return "";
        queue<TreeNode*> q;
        q.push(root);
        vector<string> vals;
        while (!q.empty()) {
            TreeNode* node = q.front();
            q.pop();
            if (node) {
                vals.push_back(to_string(node->val));
                q.push(node->left);
                q.push(node->right);
            } else {
                vals.push_back("null");
            }
        }
        while (!vals.empty() && vals.back() == "null") vals.pop_back();
        string res;
        for (size_t i = 0; i < vals.size(); ++i) {
            if (i > 0) res += ",";
            res += vals[i];
        }
        return res;
    }

    TreeNode* deserialize(string data) {
        if (data.empty()) return nullptr;
        vector<string> nodes;
        stringstream ss(data);
        string item;
        while (getline(ss, item, ',')) nodes.push_back(item);
        TreeNode* root = new TreeNode(stoi(nodes[0]));
        queue<TreeNode*> q;
        q.push(root);
        size_t idx = 1;
        while (!q.empty() && idx < nodes.size()) {
            TreeNode* node = q.front();
            q.pop();
            if (nodes[idx] != "null") {
                node->left = new TreeNode(stoi(nodes[idx]));
                q.push(node->left);
            }
            idx++;
            if (idx < nodes.size() && nodes[idx] != "null") {
                node->right = new TreeNode(stoi(nodes[idx]));
                q.push(node->right);
            }
            idx++;
        }
        return root;
    }
};
