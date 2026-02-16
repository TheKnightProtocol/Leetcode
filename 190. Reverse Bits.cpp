class Solution {
public:
    int reverseBits(int n) {
      int ans = 0;
      for(int i=0; i<32; i++ ){
      ans = ans << 1;
      int bit = (n >> i )  & 1;
      ans = ans | bit;



      }

     return ans;



    }
};
