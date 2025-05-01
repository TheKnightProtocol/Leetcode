#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        int n = nums.size();
        vector<long long> prefix(n + 1, 0);
        
        for (int i = 0; i < n; ++i) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        return countWhileMergeSort(prefix, 0, n + 1, lower, upper);
    }
    
    int countWhileMergeSort(vector<long long>& prefix, int left, int right, int lower, int upper) {
        if (right - left <= 1) return 0;
        int mid = (left + right) / 2;
        int count = countWhileMergeSort(prefix, left, mid, lower, upper) +
                    countWhileMergeSort(prefix, mid, right, lower, upper);
        
        int j = mid, k = mid, t = mid;
        vector<long long> cache;
        for (int i = left; i < mid; ++i) {
            while (k < right && prefix[k] - prefix[i] < lower) ++k;
            while (j < right && prefix[j] - prefix[i] <= upper) ++j;
            count += j - k;
        }

        // Merge the sorted halves
        int i = left, p = mid;
        while (i < mid && p < right) {
            if (prefix[i] < prefix[p])
                cache.push_back(prefix[i++]);
            else
                cache.push_back(prefix[p++]);
        }
        while (i < mid) cache.push_back(prefix[i++]);
        while (p < right) cache.push_back(prefix[p++]);
        
        for (int i = 0; i < cache.size(); ++i) {
            prefix[left + i] = cache[i];
        }

        return count;
    }
};
