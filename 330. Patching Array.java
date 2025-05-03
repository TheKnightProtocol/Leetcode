public class Solution {
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long miss = 1; // Start with 1, smallest number we cannot form
        int i = 0;

        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i]; // Use nums[i] to extend the range
                i++;
            } else {
                miss += miss; // Patch the array with miss
                patches++;
            }
        }

        return patches;
    }
}
