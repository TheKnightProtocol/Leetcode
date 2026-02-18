class Solution {
public:
    bool hasAlternatingBits(int n) {
        long num = n ^ (n >> 1);
        return (num & (num + 1)) == 0;
    }
};
