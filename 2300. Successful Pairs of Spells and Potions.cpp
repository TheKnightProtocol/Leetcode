#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    vector<int> successfulPairs(vector<int>& spells, vector<int>& potions, long long success) {
        sort(potions.begin(), potions.end());
        int n = spells.size();
        int m = potions.size();
        vector<int> result(n);
        
        for (int i = 0; i < n; i++) {
            long long spell = spells[i];
            // Calculate minimum potion strength needed
            long long minPotion = (success + spell - 1) / spell;
            
            // Binary search to find first potion >= minPotion
            int left = 0, right = m - 1;
            int idx = m; // Default to end if no potion found
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (potions[mid] >= minPotion) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            result[i] = m - idx;
        }
        
        return result;
    }
};
