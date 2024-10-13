import java.util.HashMap;

public class Solution {
    private HashMap<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // If the two strings are equal, no need to scramble
        if (s1.equals(s2)) {
            return true;
        }

        // If lengths are not equal or characters don't match, return false
        if (s1.length() != s2.length() || !areAnagrams(s1, s2)) {
            return false;
        }

        String key = s1 + "#" + s2; // Unique key for memoization
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int length = s1.length();
        // Check all possible splits
        for (int i = 1; i < length; i++) {
            // Case 1: No swap
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }
            // Case 2: Swap
            if (isScramble(s1.substring(0, i), s2.substring(length - i)) &&
                isScramble(s1.substring(i), s2.substring(0, length - i))) {
                memo.put(key, true);
                return true;
            }
        }

        // If no valid scramble was found
        memo.put(key, false);
        return false;
    }

    private boolean areAnagrams(String s1, String s2) {
        int[] count = new int[26]; // Since we only have lowercase English letters
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            count[c - 'a']--;
        }
        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Cases
        System.out.println(solution.isScramble("great", "rgeat")); // Output: true
        System.out.println(solution.isScramble("abcde", "caebd")); // Output: false
        System.out.println(solution.isScramble("a", "a"));         // Output: true
    }
}
