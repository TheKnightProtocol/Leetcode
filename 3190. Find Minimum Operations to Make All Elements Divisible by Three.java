class Solution {
    public int minimumOperations(int[] nums) {
        int ops = 0;
        for (int x : nums) ops += Math.min(x % 3, 3 - (x % 3));
        return ops;
    }
}
