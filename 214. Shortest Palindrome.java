class Solution {
    public String shortestPalindrome(String s) {
        if (s.isEmpty()) return s;  // Edge case
        
        String rev_s = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev_s;
        
        int[] lps = computeLPS(combined);
        
        // Length of the longest palindromic prefix
        int palinLen = lps[combined.length() - 1];

        // Add the remaining suffix (in reverse) at the beginning
        String suffix = s.substring(palinLen);
        return new StringBuilder(suffix).reverse().toString() + s;
    }

    // Compute the LPS (Longest Prefix Suffix) array for KMP algorithm
    private int[] computeLPS(String str) {
        int n = str.length();
        int[] lps = new int[n];
        int len = 0, i = 1;

        while (i < n) {
            if (str.charAt(i) == str.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1]; // Backtrack
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}
