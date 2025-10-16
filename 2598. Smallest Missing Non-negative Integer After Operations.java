import java.util.*;

class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        // Count frequencies of each residue modulo value
        int[] freq = new int[value];
        
        for (int num : nums) {
            // Handle negative numbers: we want non-negative remainder
            int remainder = num % value;
            if (remainder < 0) {
                remainder += value;
            }
            freq[remainder]++;
        }
        
        // For each number from 0 upwards, check if we can generate it
        // A number x can be generated if we have at least floor(x/value) + 1 numbers 
        // in the residue class of (x % value)
        for (int x = 0; x <= nums.length; x++) {
            int residue = x % value;
            int required = x / value + 1;  // How many numbers we need in this residue class
            
            if (freq[residue] < required) {
                return x;
            }
        }
        
        return nums.length;
    }
}
