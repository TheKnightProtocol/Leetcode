class Solution {
public:
    vector<string> readBinaryWatch(int turnedOn) {
        vector<string> result;
        
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (bitset<10>(h << 6 | m).count() == turnedOn) {
                    result.push_back(to_string(h) + ":" + (m < 10 ? "0" + to_string(m) : to_string(m)));
                }
            }
        }
        
        return result;
    }
};
