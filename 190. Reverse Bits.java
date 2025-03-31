public class Solution {
    public int reverseBits(int n) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1); // Append the last bit of n to result
            n = n >>> 1; // Unsigned right shift to process next bit
        }
        
        return result;
    }
}
