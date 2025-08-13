public class PowerOfThree {
    public static boolean isPowerOfThree(int n) {
        if (n <= 0) return false; // No powers of 3 for 0 or negatives
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27)); // true
        System.out.println(isPowerOfThree(0));  // false
        System.out.println(isPowerOfThree(-1)); // false
    }
}
