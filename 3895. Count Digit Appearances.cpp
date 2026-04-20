class Solution {
public:
    int countDigitOccurrences(vector<int>& nums, int digit) {
        int totalCount = 0;
        for (int num : nums) {
            if (num == 0 && digit == 0) {
                totalCount++;
                continue;
            }
            int temp = num;
            while (temp > 0) {
                if (temp % 10 == digit) {
                    totalCount++;
                }
                temp /= 10;
            }
        }
        return totalCount;
    }
};
