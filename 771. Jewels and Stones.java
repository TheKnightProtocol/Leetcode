import java.util.HashSet;

public class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        // Create a HashSet to store each jewel type
        HashSet<Character> jewelSet = new HashSet<>();
        
        // Add each character in jewels to the HashSet
        for (char jewel : jewels.toCharArray()) {
            jewelSet.add(jewel);
        }
        
        int count = 0;
        
        // Check each stone to see if it's a jewel
        for (char stone : stones.toCharArray()) {
            if (jewelSet.contains(stone)) {
                count++;
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        System.out.println(solution.numJewelsInStones("aA", "aAAbbbb")); // Output: 3
        System.out.println(solution.numJewelsInStones("z", "ZZ"));       // Output: 0
    }
}
