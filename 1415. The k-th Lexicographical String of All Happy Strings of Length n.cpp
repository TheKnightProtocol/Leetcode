class Solution {
public:
    string getHappyString(int n, int k) {
        string result;
        int count = 0;
        
        function<bool(string&)> generate = [&](string& current) {
            if (current.length() == n) {
                count++;
                if (count == k) {
                    result = current;
                    return true;
                }
                return false;
            }
            
            for (char c : {'a', 'b', 'c'}) {
                if (!current.empty() && current.back() == c) {
                    continue;
                }
                
                current.push_back(c);
                if (generate(current)) {
                    return true;
                }
                current.pop_back();
            }
            
            return false;
        };
        
        string current;
        generate(current);
        
        return result;
    }
};
