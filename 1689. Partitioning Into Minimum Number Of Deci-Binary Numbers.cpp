class Solution {
public:
    int minPartitions(string n) {
        char maxDigit = '0';
        
        // Find the maximum digit in the string
        for (char c : n) {
            if (c > maxDigit) {
                maxDigit = c;
                // Early exit if we find '9' since that's the maximum possible
                if (maxDigit == '9') {
                    return 9;
                }
            }
        }
        
        // Convert char digit to integer
        return maxDigit - '0';
    }
};
