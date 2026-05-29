class Solution {
public:
    int minElement(vector<int>& nums) {
        int minVal = INT_MAX;
        for (int num : nums) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            if (sum < minVal) minVal = sum;
        }
        return minVal;
    }
};
