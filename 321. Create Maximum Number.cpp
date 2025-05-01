#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    // Pick max subsequence of length k from a single array
    vector<int> maxSubsequence(vector<int>& nums, int k) {
        vector<int> stack;
        int drop = nums.size() - k;
        for (int num : nums) {
            while (!stack.empty() && drop > 0 && stack.back() < num) {
                stack.pop_back();
                drop--;
            }
            stack.push_back(num);
        }
        stack.resize(k);
        return stack;
    }

    // Merge two sequences into the largest possible result
    vector<int> merge(vector<int>& nums1, vector<int>& nums2) {
        vector<int> result;
        auto i = nums1.begin(), j = nums2.begin();
        while (i != nums1.end() || j != nums2.end()) {
            if (lexicographical_compare(i, nums1.end(), j, nums2.end())) {
                result.push_back(*j++);
            } else {
                result.push_back(*i++);
            }
        }
        return result;
    }

    vector<int> maxNumber(vector<int>& nums1, vector<int>& nums2, int k) {
        vector<int> best;
        int m = nums1.size(), n = nums2.size();
        for (int i = max(0, k - n); i <= min(k, m); i++) {
            vector<int> seq1 = maxSubsequence(nums1, i);
            vector<int> seq2 = maxSubsequence(nums2, k - i);
            vector<int> candidate = merge(seq1, seq2);
            if (candidate > best) best = candidate;
        }
        return best;
    }
};
