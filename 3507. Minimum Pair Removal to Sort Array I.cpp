#include <vector>
#include <climits>

using namespace std;

class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        vector<int> arr = nums;
        int ops = 0;
        
        while (true) {
            // Check if array is non-decreasing
            bool sorted = true;
            for (int i = 0; i < arr.size() - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    sorted = false;
                    break;
                }
            }
            if (sorted) break;
            
            // Find minimum sum pair
            int minSum = INT_MAX;
            int minIdx = 0;
            
            for (int i = 0; i < arr.size() - 1; i++) {
                int sum = arr[i] + arr[i + 1];
                if (sum < minSum) {
                    minSum = sum;
                    minIdx = i;
                }
            }
            
            // Merge the pair
            arr[minIdx] = minSum;
            arr.erase(arr.begin() + minIdx + 1);
            ops++;
        }
        
        return ops;
    }
};
