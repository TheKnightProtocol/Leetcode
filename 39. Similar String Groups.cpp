class Solution {
public:
    bool isSimilar(string& a, string& b) {
        if (a == b) return true;
        vector<int> diff;
        for (int i = 0; i < a.size(); i++) {
            if (a[i] != b[i]) diff.push_back(i);
            if (diff.size() > 2) return false;
        }
        if (diff.size() != 2) return false;
        return (a[diff[0]] == b[diff[1]] && a[diff[1]] == b[diff[0]]);
    }
    
    void dfs(int node, vector<vector<int>>& adj, vector<bool>& visited) {
        visited[node] = true;
        for (int nei : adj[node]) {
            if (!visited[nei]) dfs(nei, adj, visited);
        }
    }
    
    int numSimilarGroups(vector<string>& strs) {
        int n = strs.size();
        vector<vector<int>> adj(n);
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    adj[i].push_back(j);
                    adj[j].push_back(i);
                }
            }
        }
        
        vector<bool> visited(n, false);
        int groups = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                groups++;
                dfs(i, adj, visited);
            }
        }
        
        return groups;
    }
};
