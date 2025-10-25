class Solution {
    public int totalMoney(int n) {
        int total = 0;
        int week = 0;
        
        for (int day = 0; day < n; day++) {
            if (day % 7 == 0) {
                week++;
            }
            total += week + (day % 7);
        }
        
        return total;
    }
}
