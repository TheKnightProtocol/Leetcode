class Solution {
    public int countTriples(int n) {
        Set<Integer> squares = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            squares.add(i * i);
        }
        
        int count = 0;
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int sum = a * a + b * b;
                if (squares.contains(sum)) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
