class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) count++;
        }
        return count;
    }
    private boolean isGood(int num) {
        String s = Integer.toString(num);
        boolean changed = false;
        for (char c : s.toCharArray()) {
            if (c == '3' || c == '4' || c == '7') return false;
            if (c == '2' || c == '5' || c == '6' || c == '9') changed = true;
        }
        return changed;
    }
}
