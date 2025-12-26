#include <string>
#include <vector>
#include <climits>

class Solution {
public:
    int bestClosingTime(std::string customers) {
        int n = customers.size();
        std::vector<int> prefixN(n + 1, 0); // count of 'N' before hour i
        std::vector<int> suffixY(n + 1, 0); // count of 'Y' from hour i to end
        
        // prefix of N (open penalty)
        for (int i = 0; i < n; i++) {
            prefixN[i + 1] = prefixN[i] + (customers[i] == 'N' ? 1 : 0);
        }
        
        // suffix of Y (closed penalty)
        for (int i = n - 1; i >= 0; i--) {
            suffixY[i] = suffixY[i + 1] + (customers[i] == 'Y' ? 1 : 0);
        }
        
        int minPenalty = INT_MAX;
        int bestHour = 0;
        
        // try all closing hours from 0 to n
        for (int hour = 0; hour <= n; hour++) {
            int penalty = prefixN[hour] + suffixY[hour];
            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestHour = hour;
            }
        }
        
        return bestHour;
    }
};
