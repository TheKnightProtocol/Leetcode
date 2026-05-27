class Solution {
    public int numberOfSpecialChars(String word) {
        int[] firstUpper = new int[26];
        int[] lastLower = new int[26];
        // Initialize firstUpper with a large value
        for (int i = 0; i < 26; i++) {
            firstUpper[i] = Integer.MAX_VALUE;
            lastLower[i] = -1;
        }
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                int idx = c - 'a';
                lastLower[idx] = i; // update last occurrence of lowercase
            } else { // uppercase
                int idx = c - 'A';
                if (firstUpper[idx] == Integer.MAX_VALUE) {
                    firstUpper[idx] = i; // record first occurrence of uppercase
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 && firstUpper[i] != Integer.MAX_VALUE && lastLower[i] < firstUpper[i]) {
                count++;
            }
        }
        return count;
    }
}
