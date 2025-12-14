class Solution {
    public int numberOfWays(String corridor) {
        int mod = 1000000007;
        int totalSeats = 0;
        
        for (char c : corridor.toCharArray()) {
            if (c == 'S') totalSeats++;
        }
        
        if (totalSeats == 0 || totalSeats % 2 != 0) return 0;
        
        long ways = 1;
        int seats = 0;
        int lastSeatIndex = -1;
        
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                seats++;
                if (seats == 2) {
                    lastSeatIndex = i;
                } else if (seats == 3) {
                    seats = 1;
                    ways = (ways * (i - lastSeatIndex)) % mod;
                }
            }
        }
        
        return (int) ways;
    }
}
