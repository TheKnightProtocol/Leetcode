#include <vector>
using namespace std;

class Solution {
public:
    int reversePairs(vector<int>& nums) {
        return mergeSort(nums, 0, nums.size() - 1);
    }

    int mergeSort(vector<int>& nums, int left, int right) {
        if (left >= right) return 0;

        int mid = (left + right) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        int j = mid + 1;
        for (int i = left; i <= mid; ++i) {
            while (j <= right && (long long)nums[i] > 2LL * nums[j])
                ++j;
            count += j - (mid + 1);
        }

        vector<int> sorted;
        int i = left;
        j = mid + 1;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) sorted.push_back(nums[i++]);
            else sorted.push_back(nums[j++]);
        }
        while (i <= mid) sorted.push_back(nums[i++]);
        while (j <= right) sorted.push_back(nums[j++]);

        for (int k = left; k <= right; ++k)
            nums[k] = sorted[k - left];

        return count;
    }
};
