class Solution {
public:
    bool validUtf8(vector<int>& data) {
        int i = 0;
        while (i < data.size()) {
            int first = data[i];
            if ((first & 0x80) == 0) {
                i++;
                continue;
            }
            int bytes = 0;
            if ((first & 0xE0) == 0xC0) bytes = 2;
            else if ((first & 0xF0) == 0xE0) bytes = 3;
            else if ((first & 0xF8) == 0xF0) bytes = 4;
            else return false;
            if (i + bytes > data.size()) return false;
            for (int j = 1; j < bytes; j++) {
                if ((data[i + j] & 0xC0) != 0x80) return false;
            }
            i += bytes;
        }
        return true;
    }
};
