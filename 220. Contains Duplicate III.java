import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0 || t < 0) return false;

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long) nums[i] + t);
            Long ceil = set.ceiling((long) nums[i] - t);

            if ((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i])) {
                return true;
            }

            set.add((long) nums[i]);

            if (set.size() > k) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3, t1 = 0;
        System.out.println(solution.containsNearbyAlmostDuplicate(nums1, k1, t1)); // Output: true

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1, t2 = 2;
        System.out.println(solution.containsNearbyAlmostDuplicate(nums2, k2, t2)); // Output: true

        int[] nums3 = {1, 5, 9, 1, 5, 9};
        int k3 = 2, t3 = 3;
        System.out.println(solution.containsNearbyAlmostDuplicate(nums3, k3, t3)); // Output: false
    }
}
