class Solution {
public:
    vector<int> sortByBits(vector<int>& arr) {
        auto countBits = [](int num) {
            int count = 0;
            while (num > 0) {
                count += num & 1;
                num >>= 1;
            }
            return count;
        };
        
        sort(arr.begin(), arr.end(), [&](int a, int b) {
            int bitsA = countBits(a);
            int bitsB = countBits(b);
            
            if (bitsA != bitsB) {
                return bitsA < bitsB;
            }
            return a < b;
        });
        
        return arr;
    }
};
