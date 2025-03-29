class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--; // Adjust for 1-based indexing
            char letter = (char) ('A' + (columnNumber % 26));
            result.append(letter);
            columnNumber /= 26;
        }

        return result.reverse().toString(); // Reverse because we extracted from least to most significant
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.convertToTitle(1));    // Output: "A"
        System.out.println(sol.convertToTitle(28));   // Output: "AB"
        System.out.println(sol.convertToTitle(701));  // Output: "ZY"
        System.out.println(sol.convertToTitle(52));   // Output: "AZ"
        System.out.println(sol.convertToTitle(2147483647)); // Large case
    }
}
