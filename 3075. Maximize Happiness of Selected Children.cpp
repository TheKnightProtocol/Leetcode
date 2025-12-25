#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    long long maximumHappinessSum(vector<int>& happiness, int k) {
        sort(happiness.begin(), happiness.end(), greater<int>());
        
        long long totalHappiness = 0;
        for(int turn = 0; turn < k; turn++) {
            long long currentHappiness = (long long)happiness[turn] - turn;
            if(currentHappiness > 0) {
                totalHappiness += currentHappiness;
            }
        }
        
        return totalHappiness;
    }
};
