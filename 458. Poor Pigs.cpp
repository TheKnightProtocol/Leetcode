class Solution {
public:
    int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int tests = minutesToTest / minutesToDie;
        int pigs = 0;
        while (pow(tests + 1, pigs) < buckets) {
            pigs++;
        }
        return pigs;
    }
};
