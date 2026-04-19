class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        if (n == 0) return 0;
        
        int rest = 0;
        int hold = -prices[0];
        int sold = 0;
        
        for (int i = 1; i < n; ++i) {
            int prev_rest = rest;
            int prev_hold = hold;
            int prev_sold = sold;
            
            rest = max(prev_rest, prev_sold);
            hold = max(prev_hold, prev_rest - prices[i]);
            sold = prev_hold + prices[i];
        }
        
        return max(rest, sold);
    }
};
