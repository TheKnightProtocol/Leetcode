class Solution {
public:
    long long minNumberOfSeconds(int mountainHeight, vector<int>& workerTimes) {
        long long left = 1;
        long long right = (long long) *max_element(workerTimes.begin(), workerTimes.end()) * 
                         (long long) mountainHeight * (mountainHeight + 1) / 2;
        long long answer = right;
        
        while (left <= right) {
            long long mid = left + (right - left) / 2;
            long long total = 0;
            
            for (int t : workerTimes) {
                long long x = floor((-1 + sqrt(1 + 8.0 * mid / t)) / 2);
                total += x;
                if (total >= mountainHeight) break;
            }
            
            if (total >= mountainHeight) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
};
