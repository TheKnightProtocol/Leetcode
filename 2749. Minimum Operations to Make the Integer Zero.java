class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        long n1 = num1;
        long n2 = num2;
        for (int k = 0; k <= 100; k++) {
            long T = n1 - k * n2;
            if (T < 0) continue;
            int bits = Long.bitCount(T);
            if (bits <= k && k <= T) {
                return k;
            }
        }
        return -1;
    }
}
