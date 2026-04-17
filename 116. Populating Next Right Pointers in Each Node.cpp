class Solution {
public:
    Node* connect(Node* root) {
        if (!root) return nullptr;
         
        Node* levelStart = root;
        
        while (levelStart->left) {
            Node* curr = levelStart;
            
            while (curr) {
                curr->left->next = curr->right;
                
                if (curr->next) {
                    curr->right->next = curr->next->left;
                }
                
                curr = curr->next;
            }
            
            levelStart = levelStart->left;
        }
        
        return root;
    }
};
