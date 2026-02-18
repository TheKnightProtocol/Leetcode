class Solution {
public:
    int intersectionSizeTwo(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), [](const vector<int>& a, const vector<int>& b) {
            if (a[1] == b[1]) {
                return a[0] > b[0];
            }
            return a[1] < b[1];
        });
        
        vector<int> nums;
        
        for (auto& interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            int count = 0;
            for (int num : nums) {
                if (num >= start && num <= end) {
                    count++;
                }
            }
            
            if (count == 0) {
                nums.push_back(end - 1);
                nums.push_back(end);
            } else if (count == 1) {
                nums.push_back(end);
            }
        }
        
        return nums.size();
    }
};
