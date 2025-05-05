class Solution {
public:
    int largestPalindrome(int n) {
        if (n == 1) return 9;

        long upper = pow(10, n) - 1;
        long lower = pow(10, n - 1);

        for (long left = upper; left >= lower; --left) {
            // Create palindrome by appending reverse of left
            string strLeft = to_string(left);
            string revStrLeft(strLeft.rbegin(), strLeft.rend());
            long palin = stol(strLeft + revStrLeft);

            for (long x = upper; x * x >= palin; --x) {
                if (palin % x == 0) {
                    return palin % 1337;
                }
            }
        }

        return -1;
    }
};
