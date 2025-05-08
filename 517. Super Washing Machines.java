public class Solution {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int totalDresses = 0;
        
        for (int dresses : machines) {
            totalDresses += dresses;
        }

        if (totalDresses % n != 0) {
            return -1;
        }

        int target = totalDresses / n;
        int moves = 0;
        int currentBalance = 0;

        for (int i = 0; i < n; i++) {
            int diff = machines[i] - target;
            currentBalance += diff;
            moves = Math.max(moves, Math.max(Math.abs(currentBalance), diff));
        }

        return moves;
    }
}
