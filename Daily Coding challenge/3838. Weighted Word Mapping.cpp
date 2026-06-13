#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    string mapWeight(vector<string>& words, vector<int>& weights) {
        string res;
        for (const string& w : words) {
            int sum = 0;
            for (char c : w) {
                sum += weights[c - 'a'];
            }
            int v = sum % 26;
            res += char('z' - v);
        }
        return res;
    }
};
