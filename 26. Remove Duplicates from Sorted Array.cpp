#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if (nums.empty()) return 0;

        int k = 1; // Index to place the next unique element

        // Loop through the array starting from the second element
        for (int i = 1; i < nums.size(); i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }
        
        return k;
    }
};

int test() { // Renamed main to test
    vector<int> nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    
    // Create an instance of the Solution class
    Solution solution;
    int k = solution.removeDuplicates(nums);

    // Output the results
    cout << "Number of unique elements: " << k << endl;
    cout << "Modified array: ";
    for (int i = 0; i < k; i++) {
        cout << nums[i] << " ";
    }
    cout << endl;

    return 0;
}
 
