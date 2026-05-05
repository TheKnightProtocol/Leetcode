class Solution {
    public int integerReplacement(int n) {
        int steps = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else {
                if (n == 3) {
                    steps += 2;
                    break;
                }
                if ((n & 3) == 3) {
                    n = (n >>> 1) + 1;
                } else {
                    n >>>= 1;
                }
                steps++;
            }
            steps++;
        }
        return steps;
    }
}
