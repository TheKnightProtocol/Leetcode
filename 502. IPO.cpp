#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

class Solution {
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        int n = profits.size();
        vector<pair<int, int>> projects(n);
        
        // Pair capital with profit
        for (int i = 0; i < n; ++i) {
            projects[i] = {capital[i], profits[i]};
        }
        
        // Sort projects by capital needed
        sort(projects.begin(), projects.end());
        
        priority_queue<int> maxHeap;
        int idx = 0;

        for (int i = 0; i < k; ++i) {
            // Push all affordable projects to maxHeap
            while (idx < n && projects[idx].first <= w) {
                maxHeap.push(projects[idx].second);
                ++idx;
            }

            // If no project can be done, break
            if (maxHeap.empty()) break;

            // Select most profitable affordable project
            w += maxHeap.top();
            maxHeap.pop();
        }

        return w;
    }
};
