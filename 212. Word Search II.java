import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word = null;  // Stores the complete word at the end node
}

class Trie {
    TrieNode root = new TrieNode();
    
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.word = word;  // Mark the end of the word
    }
}

class Solution {
    private int m, n;
    private Set<String> result = new HashSet<>();
    
    public List<String> findWords(char[][] board, String[] words) {
        // Step 1: Build Trie
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        
        m = board.length;
        n = board[0].length;
        TrieNode root = trie.root;
        
        // Step 2: DFS on each board cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (root.children.containsKey(board[i][j])) {
                    dfs(board, i, j, root);
                }
            }
        }
        
        return new ArrayList<>(result);
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode node) {
        char ch = board[i][j];
        TrieNode nextNode = node.children.get(ch);
        
        if (nextNode == null) return;
        
        // If a word is found, add it to the result set
        if (nextNode.word != null) {
            result.add(nextNode.word);
            nextNode.word = null;  // Avoid duplicate words
        }
        
        // Mark cell as visited
        board[i][j] = '#';
        
        // Explore 4 possible directions: Up, Down, Left, Right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newX = i + dir[0];
            int newY = j + dir[1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] != '#') {
                dfs(board, newX, newY, nextNode);
            }
        }
        
        // Restore board state (backtracking)
        board[i][j] = ch;
        
        // Optimization: Remove node if it has no children (pruning)
        if (nextNode.children.isEmpty()) {
            node.children.remove(ch);
        }
    }
}
