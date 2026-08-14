class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26]; // Fixed array type
        int left = 0;
        int res = 0;

        for (int right = 0; right < s.length(); right++) {
            int ch = s.charAt(right) - 'a';
            count[ch]++;

            // Shrink window until the frequency of s[right] is <= 2
            while (count[ch] > 2) {
                int leftChar = s.charAt(left) - 'a'; // Fixed declaration
                count[leftChar]--;                   // Decrement count for left char
                left++;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
