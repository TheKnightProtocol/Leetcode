class Solution {
    public long getDescentPeriods(int[] prices) {
        long count = 0;
        int currentLength = 1;
        
        for (int i = 0; i < prices.length; i++) {
            if (i > 0 && prices[i] == prices[i - 1] - 1) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            count += currentLength;
        }
        
        return count;
    }
}
