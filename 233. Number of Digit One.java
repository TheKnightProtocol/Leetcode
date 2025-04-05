public class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        long i = 1;  // Use long to prevent overflow for large n

        while (i <= n) {
            long higher = n / (i * 10);
            long current = (n / i) % 10;
            long lower = n % i;

            if (current == 0) {
                count += higher * i;
            } else if (current == 1) {
                count += higher * i + (lower + 1);
            } else {
                count += (higher + 1) * i;
            }

            i *= 10;
        }

        return count;
    }
}
