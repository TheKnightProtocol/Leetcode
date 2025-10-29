class Solution {
    public int smallestNumber(int n) {
        if (n == 0) return 1;
        
        String binary = Integer.toBinaryString(n);
        int len = binary.length();
        
        // Check if the number is already all ones
        if (binary.indexOf('0') == -1) {
            return n;
        }
        
        // The smallest number with all ones is (2^len - 1)
        return (1 << len) - 1;
    }
}
