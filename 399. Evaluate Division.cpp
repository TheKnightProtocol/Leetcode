#include <vector>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <functional>
using namespace std;

class Solution {
public:
    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        // Build graph
        unordered_map<string, vector<pair<string, double>>> graph;
        unordered_set<string> vars;
        for (int i = 0; i < equations.size(); ++i) {
            string a = equations[i][0], b = equations[i][1];
            double val = values[i];
            graph[a].emplace_back(b, val);
            graph[b].emplace_back(a, 1.0 / val);
            vars.insert(a);
            vars.insert(b);
        }
        
        // DFS function
        function<double(const string&, const string&, unordered_set<string>&)> dfs = [&](const string& cur, const string& target, unordered_set<string>& visited) -> double {
            if (cur == target) return 1.0;
            visited.insert(cur);
            for (auto& [nei, w] : graph[cur]) {
                if (visited.find(nei) == visited.end()) {
                    double res = dfs(nei, target, visited);
                    if (res != -1.0) return w * res;
                }
            }
            return -1.0;
        };
        
        vector<double> ans;
        for (auto& q : queries) {
            string c = q[0], d = q[1];
            if (vars.find(c) == vars.end() || vars.find(d) == vars.end()) {
                ans.push_back(-1.0);
                continue;
            }
            if (c == d) {
                ans.push_back(1.0);
                continue;
            }
            unordered_set<string> visited;
            double res = dfs(c, d, visited);
            ans.push_back(res);
        }
        return ans;
    }
};
