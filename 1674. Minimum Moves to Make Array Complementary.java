class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int half = n / 2;
        int maxSum = 2 * limit;
        int[] diff = new int[maxSum + 3]; // indices from 2 to 2*limit
        int[] sumCount = new int[maxSum + 3];
        
        for (int i = 0; i < half; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            int low = Math.min(a, b);
            int high = Math.max(a, b);
            int left = low + 1;
            int right = high + limit;
            diff[left]++;
            diff[right + 1]--;
            sumCount[a + b]++;
        }
        
        int best = Integer.MAX_VALUE;
        int curr = 0;
        int totalPairs = half;
        for (int t = 2; t <= maxSum; t++) {
            curr += diff[t];
            int moves = 2 * totalPairs - curr - sumCount[t];
            if (moves < best) best = moves;
        }
        return best;
    }
}
