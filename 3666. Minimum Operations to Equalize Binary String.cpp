#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>
#include <set>

using namespace std;

class Solution {
public:
    int minOperations(string s, int k) {
        int n = s.length();
        int initial_zeros = 0;
        for (char c : s) if (c == '0') initial_zeros++;

        if (initial_zeros == 0) return 0;

        vector<int> dist(n + 1, -1);
        queue<int> q;
        
        set<int> unvisited_even, unvisited_odd;
        for (int i = 0; i <= n; ++i) {
            if (i == initial_zeros) continue;
            if (i % 2 == 0) unvisited_even.insert(i);
            else unvisited_odd.insert(i);
        }

        q.push(initial_zeros);
        dist[initial_zeros] = 0;

        while (!q.empty()) {
            int z = q.front();
            q.pop();

            int min_i = max(0, k - (n - z));
            int max_i = min(z, k);

            int min_z_next = z + k - 2 * max_i;
            int max_z_next = z + k - 2 * min_i;

            set<int>& target_set = (min_z_next % 2 == 0) ? unvisited_even : unvisited_odd;
            
            auto it = target_set.lower_bound(min_z_next);
            while (it != target_set.end() && *it <= max_z_next) {
                int next_z = *it;
                dist[next_z] = dist[z] + 1;
                if (next_z == 0) return dist[next_z];
                q.push(next_z);
                it = target_set.erase(it);
            }
        }

        return dist[0];
    }
};
