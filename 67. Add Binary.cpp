class Solution {
public:
    string addBinary(string a, string b) {
        string result = "";
        int i = a.length() - 1;  // Changed variable name and added ()
        int j = b.length() - 1;  // Changed variable name and added ()
        int carry = 0;
        
        while (true) {
            if (i < 0 && j < 0 && carry == 0) {  // Changed = to ==
                break;
            }
            
            int sum = carry;
            
            if (i >= 0) {
                sum += a[i] - '0';  // Added '' around 0
                i--;                 // Added semicolon
            }
            
            if (j >= 0) {
                sum += b[j] - '0';  // Changed a[j] to b[j] and added '' around 0
                j--;                 // Added semicolon
            }
            
            carry = sum / 2;
            result = char(sum % 2 + '0') + result;  // Added + sign
        }
        
        return result;
    }
};
