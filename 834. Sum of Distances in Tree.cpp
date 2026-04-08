class Solution {
public:
    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<vector<int>> adj(n);
        for (auto& e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }
        
        vector<int> count(n, 1);
        vector<int> ans(n, 0);
        
        function<void(int, int)> dfs1 = [&](int node, int parent) {
            for (int child : adj[node]) {
                if (child == parent) continue;
                dfs1(child, node);
                count[node] += count[child];
                ans[node] += ans[child] + count[child];
            }
        };
        
        function<void(int, int)> dfs2 = [&](int node, int parent) {
            for (int child : adj[node]) {
                if (child == parent) continue;
                ans[child] = ans[node] - count[child] + (n - count[child]);
                dfs2(child, node);
            }
        };
        
        dfs1(0, -1);
        dfs2(0, -1);
        
        return ans;
    }
};
