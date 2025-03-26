public class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        
        for (int num : nums) {
            ones = (ones ^ num) & ~twos;  // Add current num to "ones" if it's not in "twos"
            twos = (twos ^ num) & ~ones;  // Add current num to "twos" if it's not in "ones"
        }
        
        return ones;  // "ones" contains the unique number
    }
}
