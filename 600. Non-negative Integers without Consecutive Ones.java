class Solution {
    public int findIntegers(int n) {
        int[] fib = new int[32];
        fib[0] = 1;
        fib[1] = 2;
        for (int i = 2; i < 32; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        int result = 0, k = 30, prevBit = 0;
        while (k >= 0) {
            if ((n & (1 << k)) != 0) {
                result += fib[k];
                if (prevBit == 1) return result;
                prevBit = 1;
            } else {
                prevBit = 0;
            }
            k--;
        }
        return result + 1;
    }
}
