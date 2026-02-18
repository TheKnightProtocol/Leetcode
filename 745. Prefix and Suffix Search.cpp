class WordFilter {
private:
    unordered_map<string, int> prefixSuffixMap;
    
public:
    WordFilter(vector<string>& words) {
        for (int idx = 0; idx < words.size(); idx++) {
            string word = words[idx];
            int len = word.length();
            
            // Generate all possible prefix-suffix combinations
            for (int i = 1; i <= len; i++) {
                string prefix = word.substr(0, i);
                for (int j = 0; j < len; j++) {
                    string suffix = word.substr(j);
                    string key = prefix + "#" + suffix;
                    prefixSuffixMap[key] = idx;
                }
            }
        }
    }
    
    int f(string pref, string suff) {
        string key = pref + "#" + suff;
        if (prefixSuffixMap.count(key)) {
            return prefixSuffixMap[key];
        }
        return -1;
    }
};
