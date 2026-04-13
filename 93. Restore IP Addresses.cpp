class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> result;
        string current;
        backtrack(s, 0, 0, current, result);
        return result;
    }
    
private:
    void backtrack(const string& s, int start, int segment, string& current, vector<string>& result) {
        if (segment == 4 && start == s.length()) {
            result.push_back(current);
            return;
        }
        
        if (segment == 4 || start == s.length()) return;
        
        for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
            string part = s.substr(start, len);
            
            if (len > 1 && part[0] == '0') break;
            
            int num = stoi(part);
            if (num > 255) break;
            
            string prev = current;
            if (!current.empty()) current += ".";
            current += part;
            
            backtrack(s, start + len, segment + 1, current, result);
            
            current = prev;
        }
    }
};
