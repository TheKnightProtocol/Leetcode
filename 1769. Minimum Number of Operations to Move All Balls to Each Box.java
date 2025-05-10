public class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] result = new int[n];
        
        // Left to right pass
        int leftOps = 0, leftBalls = 0;
        for (int i = 0; i < n; i++) {
            result[i] = leftOps;
            if (boxes.charAt(i) == '1') {
                leftBalls++;
            }
            leftOps += leftBalls;
        }
        
        // Right to left pass
        int rightOps = 0, rightBalls = 0;
        for (int i = n - 1; i >= 0; i--) {
            result[i] += rightOps;
            if (boxes.charAt(i) == '1') {
                rightBalls++;
            }
            rightOps += rightBalls;
        }
        
        return result;
    }
}
