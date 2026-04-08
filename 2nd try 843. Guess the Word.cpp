class Solution {
public:
    int match(string& a, string& b) {
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            if (a[i] == b[i]) cnt++;
        }
        return cnt;
    }
    
    void findSecretWord(vector<string>& words, Master& master) {
        vector<string> candidates = words;
        
        while (true) {
            int bestIdx = 0;
            int bestMax = candidates.size();
            
            for (int i = 0; i < candidates.size(); i++) {
                vector<int> groups(7, 0);
                for (int j = 0; j < candidates.size(); j++) {
                    if (i == j) continue;
                    int m = match(candidates[i], candidates[j]);
                    groups[m]++;
                }
                int curMax = *max_element(groups.begin(), groups.end());
                if (curMax < bestMax) {
                    bestMax = curMax;
                    bestIdx = i;
                }
            }
            
            string guess = candidates[bestIdx];
            int matches = master.guess(guess);
            
            if (matches == 6) return;
            
            vector<string> newCandidates;
            for (string& w : candidates) {
                if (match(guess, w) == matches) {
                    newCandidates.push_back(w);
                }
            }
            candidates = newCandidates;
        }
    }
};
