#include <string>
#include <vector>

class Solution {
public:
    std::string decodeCiphertext(std::string encodedText, int rows) {
        int n = encodedText.length();
        if (rows == 1 || n == 0) return encodedText;
        
        int cols = n / rows;
        std::vector<std::string> matrix(rows);
        for (int i = 0; i < rows; i++) {
            matrix[i] = encodedText.substr(i * cols, cols);
        }
        
        std::string result;
        for (int k = 0; k < cols; k++) {
            int r = 0, c = k;
            while (r < rows && c < cols) {
                result += matrix[r][c];
                r++;
                c++;
            }
        }
        
        while (!result.empty() && result.back() == ' ') {
            result.pop_back();
        }
        
        return result;
    }
};
