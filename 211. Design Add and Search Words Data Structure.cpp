class WordDictionary {
private:
    struct TrieNode {
        TrieNode* children[26];
        bool isEnd;
        
        TrieNode() {
            for (int i = 0; i < 26; i++) {
                children[i] = nullptr;
            }
            isEnd = false;
        }
    };
    
    TrieNode* root;
    
    bool searchHelper(TrieNode* node, const string& word, int index) {
        if (index == word.length()) {
            return node->isEnd;
        }
        
        char c = word[index];
        
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node->children[i] && searchHelper(node->children[i], word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int idx = c - 'a';
            if (node->children[idx] == nullptr) {
                return false;
            }
            return searchHelper(node->children[idx], word, index + 1);
        }
    }
    
public:
    WordDictionary() {
        root = new TrieNode();
    }
    
    void addWord(string word) {
        TrieNode* node = root;
        for (char c : word) {
            int idx = c - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new TrieNode();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }
    
    bool search(string word) {
        return searchHelper(root, word, 0);
    }
};
