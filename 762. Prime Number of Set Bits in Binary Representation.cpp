class Solution {
public:
    int countPrimeSetBits(int left, int right) {
    int prime = 0;
    for(int p : {2,3,5,7,11,13,17,19}) prime |= 1 << p;
    int count = 0;
    for(int i = left; i<= right ; i++ ) {
        if (prime &(1 << __builtin_popcount(i))) count++;







    }


    return count;





    }
};
