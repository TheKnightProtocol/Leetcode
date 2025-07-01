public class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; // handle overflow
        }

        // Convert both numbers to long and work with negatives to handle overflow
        long lDividend = Math.abs((long) dividend);
        long lDivisor = Math.abs((long) divisor);
        int sign = (dividend > 0) == (divisor > 0) ? 1 : -1;

        long result = 0;
        while (lDividend >= lDivisor) {
            long temp = lDivisor, multiple = 1;
            while (lDividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            lDividend -= temp;
            result += multiple;
        }

        result = sign * result;

        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) result;
    }
}
