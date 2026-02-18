class Solution {
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        vector<vector<string>> result;
        unordered_set<string> dict(wordList.begin(), wordList.end());
        
        if (dict.find(endWord) == dict.end()) {
            return result;
        }
        
        // BFS to find shortest path and build graph
        unordered_map<string, vector<string>> graph;
        unordered_map<string, int> distance;
        queue<string> q;
        
        q.push(beginWord);
        distance[beginWord] = 1;
        
        while (!q.empty()) {
            string current = q.front();
            q.pop();
            
            if (current == endWord) {
                continue;
            }
            
            string next = current;
            for (int i = 0; i < current.length(); i++) {
                char original = current[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == original) continue;
                    next[i] = c;
                    
                    if (dict.find(next) != dict.end()) {
                        if (distance.find(next) == distance.end()) {
                            distance[next] = distance[current] + 1;
                            graph[next].push_back(current);
                            q.push(next);
                        } else if (distance[next] == distance[current] + 1) {
                            graph[next].push_back(current);
                        }
                    }
                }
                next[i] = original;
            }
        }
        
        // DFS to reconstruct paths
        vector<string> path;
        path.push_back(endWord);
        dfs(endWord, beginWord, graph, path, result);
        
        // Reverse each path to start from beginWord
        for (auto& p : result) {
            reverse(p.begin(), p.end());
        }
        
        return result;
    }
    
    void dfs(string& current, string& beginWord, unordered_map<string, vector<string>>& graph, 
             vector<string>& path, vector<vector<string>>& result) {
        if (current == beginWord) {
            result.push_back(path);
            return;
        }
        
        for (string& neighbor : graph[current]) {
            path.push_back(neighbor);
            dfs(neighbor, beginWord, graph, path, result);
            path.pop_back();
        }
    }
};
