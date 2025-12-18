#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    long long maxProfit(vector<int>& prices, vector<int>& strategy, int k) {
        int n = prices.size();
        int half = k / 2;
        long long originalProfit = 0;
        
        for (int i = 0; i < n; i++) {
            originalProfit += (long long)strategy[i] * prices[i];
        }
        
        long long maxProfit = originalProfit;
        
        vector<long long> leftSum(n + 1, 0);
        vector<long long> rightSum(n + 1, 0);
        
        for (int i = 0; i < n; i++) {
            leftSum[i + 1] = leftSum[i] + (long long)strategy[i] * prices[i];
            rightSum[i + 1] = rightSum[i] + prices[i];
        }
        
        for (int start = 0; start <= n - k; start++) {
            int end = start + k - 1;
            int mid = start + half;
            
            long long newProfit = originalProfit;
            
            newProfit -= leftSum[mid] - leftSum[start];
            newProfit -= leftSum[end + 1] - leftSum[mid];
            newProfit += rightSum[end + 1] - rightSum[mid];
            
            maxProfit = max(maxProfit, newProfit);
        }
        
        return maxProfit;
    }
};
