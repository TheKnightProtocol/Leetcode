class Solution {
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = "0123456789abcdef".toCharArray();
        String res = "";
        while (num != 0) {
            res = map[num & 15] + res;
            num >>>= 4;
        }
        return res;
    }
}
