#include <vector>
#include <random>
#include <algorithm>
using namespace std;

class Solution {
private:
    vector<vector<int>> rects;
    vector<int> prefix_areas;
    int total;
    mt19937 rng;
public:
    Solution(vector<vector<int>>& rects) : rects(rects), rng(random_device{}()) {
        prefix_areas.reserve(rects.size());
        int sum = 0;
        for (auto& r : rects) {
            int width = r[2] - r[0] + 1;
            int height = r[3] - r[1] + 1;
            int area = width * height;
            sum += area;
            prefix_areas.push_back(sum);
        }
        total = sum;
    }
    
    vector<int> pick() {
        uniform_int_distribution<int> dist(1, total);
        int rand_val = dist(rng);
        // binary search to find rectangle
        auto it = lower_bound(prefix_areas.begin(), prefix_areas.end(), rand_val);
        int idx = it - prefix_areas.begin();
        vector<int>& r = rects[idx];
        uniform_int_distribution<int> x_dist(r[0], r[2]);
        uniform_int_distribution<int> y_dist(r[1], r[3]);
        return {x_dist(rng), y_dist(rng)};
    }
};
