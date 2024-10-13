import java.util.HashMap;

public class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        // Frequency map for characters in t
        HashMap<Character, Integer> tCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }

        // Variables to track the number of unique characters in t
        int required = tCount.size();
        int formed = 0;

        // Two pointers for the sliding window
        HashMap<Character, Integer> windowCounts = new HashMap<>();
        int l = 0, r = 0;
        int[] ans = { -1, 0, 0 }; // [length of window, left, right]

        while (r < s.length()) {
            char c = s.charAt(r);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // If the current character's frequency matches the desired count in t
            if (tCount.containsKey(c) && windowCounts.get(c).intValue() == tCount.get(c).intValue()) {
                formed++;
            }

            // Contract the window until it's no longer valid
            while (l <= r && formed == required) {
                c = s.charAt(l);

                // Save the smallest window and its length
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1; // update length
                    ans[1] = l;         // update left index
                    ans[2] = r;         // update right index
                }

                // Remove the leftmost character from the window
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (tCount.containsKey(c) && windowCounts.get(c).intValue() < tCount.get(c).intValue()) {
                    formed--;
                }
                l++;
            }

            // Expand the window
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Cases
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC")); // Output: "BANC"
        System.out.println(solution.minWindow("a", "a"));               // Output: "a"
        System.out.println(solution.minWindow("a", "aa"));              // Output: ""
    }
}
