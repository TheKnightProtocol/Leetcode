class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        long expected = (long) n * (n + 1) / 2;
        long actual = 0;
        for (int x : nums) actual += x;
        return (int)(expected - actual);
    }
}
