class Solution {
public:
    int binaryGap(int n) {
        int maxd = 0, prev = -1, pos = 0;
        while (n) {
          if ( n & 1) {
          if (prev != -1 ) maxd = max(maxd, pos - prev); 
          prev = pos;
          }
          n >>= 1;
          pos++;

        } 
        return maxd;
    }






};

