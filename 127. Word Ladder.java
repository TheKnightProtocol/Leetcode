import java.util.*;
        
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0; // End word must be in the word list

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1; // Start with level 1 (including beginWord)

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                
                // If the endWord is reached, return the transformation length
                if (word.equals(endWord)) return level;
                
                // Try changing each letter in the word
                for (int j = 0; j < word.length(); j++) {
                    char[] wordChars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        char originalChar = wordChars[j];
                        if (wordChars[j] == c) continue; // Skip same character
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        
                        if (wordSet.contains(newWord)) {
                            queue.add(newWord);
                            wordSet.remove(newWord); // Remove to prevent revisiting
                        }
                        wordChars[j] = originalChar; // Restore original character
                    }
                }
            }
            level++; // Increase transformation sequence length
        }
        return 0; // No valid transformation sequence found
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> wordList1 = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(solution.ladderLength("hit", "cog", wordList1)); // Output: 5
        
        List<String> wordList2 = Arrays.asList("hot","dot","dog","lot","log");
        System.out.println(solution.ladderLength("hit", "cog", wordList2)); // Output: 0
    }
}
