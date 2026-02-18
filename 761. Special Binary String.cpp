class Solution {
public:
    string makeLargestSpecial(string s) {
        if (s.empty()) return "";
        
        vector<string> specials;
        int count = 0;
        int start = 0;
        
        for (int i = 0; i < s.length(); i++) {
            count += (s[i] == '1') ? 1 : -1;
            if (count == 0) {
                string inner = s.substr(start + 1, i - start - 1);
                specials.push_back("1" + makeLargestSpecial(inner) + "0");
                start = i + 1;
            }
        }
        
        sort(specials.begin(), specials.end(), greater<string>());
        
        string result;
        for (string& sp : specials) {
            result += sp;
        }
        return result;
    }
};
