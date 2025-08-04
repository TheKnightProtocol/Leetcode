import java.util.HashSet;
import java.util.Set;

public class Solution {

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int digit = n % 10;
            totalSum += digit * digit;
            n /= 10;
        }
        return totalSum;
    }

    // Optional: main method for testing locally
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isHappy(19)); // true
        System.out.println(sol.isHappy(2));  // false
    }
}
