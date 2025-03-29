public class Solution {
    public String reverseWords(String s) {
        // Trim leading and trailing spaces and split words by multiple spaces
        String[] words = s.trim().split("\\s+");
        
        // Reverse the words
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("the sky is blue")); // Output: "blue is sky the"
        System.out.println(solution.reverseWords("  hello world  ")); // Output: "world hello"
        System.out.println(solution.reverseWords("a good   example")); // Output: "example good a"
    }
}
