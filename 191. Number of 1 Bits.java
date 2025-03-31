public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1); // Remove the lowest set bit
            count++;
        }
        return count;
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.hammingWeight(11));         // Output: 3
        System.out.println(sol.hammingWeight(128));        // Output: 1
        System.out.println(sol.hammingWeight(2147483645)); // Output: 30
    }
}
