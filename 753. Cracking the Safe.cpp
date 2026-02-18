class Solution {
public:
    string crackSafe(int n, int k) {
        string ans = string(n, '0');
        unordered_set<string> visited;
        visited.insert(ans);
        
        int total = pow(k, n);
        dfs(ans, visited, total, n, k);
        
        return ans;
    }
    
private:
    bool dfs(string& ans, unordered_set<string>& visited, int total, int n, int k) {
        if (visited.size() == total) {
            return true;
        }
        
        string last = ans.substr(ans.length() - n + 1);
        for (char c = '0'; c < '0' + k; c++) {
            string next = last + c;
            if (!visited.count(next)) {
                visited.insert(next);
                ans.push_back(c);
                if (dfs(ans, visited, total, n, k)) {
                    return true;
                }
                ans.pop_back();
                visited.erase(next);
            }
        }
        return false;
    }
};
