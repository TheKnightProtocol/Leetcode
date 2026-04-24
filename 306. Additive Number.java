import java.math.BigInteger;

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i) {
            if (num.charAt(0) == '0' && i > 1) break;
            for (int j = 1; Math.max(i, j) <= n - i - j; ++j) {
                if (num.charAt(i) == '0' && j > 1) break;
                if (isValid(i, j, num)) return true;
            }
        }
        return false;
    }

    private boolean isValid(int i, int j, String num) {
        BigInteger a = new BigInteger(num.substring(0, i));
        BigInteger b = new BigInteger(num.substring(i, i + j));
        String rest = num.substring(i + j);
        while (!rest.isEmpty()) {
            b = a.add(b);
            a = b.subtract(a);
            String sumStr = b.toString();
            if (!rest.startsWith(sumStr)) return false;
            rest = rest.substring(sumStr.length());
        }
        return true;
    }
}
