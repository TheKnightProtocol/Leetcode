class Solution {
    static char op = '(';
    public List<String> generateParenthesis(int n) {
        if (n-- == 1)
            return List.of("()");

        List<String> res = new ArrayList<>();
        int sz = n << 1;
        int mask = (1 << n) - 1;

        while (mask < 1 << sz) {
            String s = "(";
            int bal = 1;

            for (int i = 0; i < sz; i++) {
                int b = (mask >> i) & 1;
                bal += 1 - (b << 1);

                if (bal < 0)
                    break;

                s += (char) (op | b);
            }

            if (s.length() - 1 == sz)
                res.add(s + ")");

            int c = mask & -mask;
            int r = mask + c;
            mask = (((r ^ mask) >> 2) / c) | r;
        }

        return res;
    }
}
