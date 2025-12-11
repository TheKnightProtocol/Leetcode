import java.util.*;

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, Integer> colMin = new HashMap<>();
        Map<Integer, Integer> colMax = new HashMap<>();
        Map<Integer, Integer> rowMin = new HashMap<>();
        Map<Integer, Integer> rowMax = new HashMap<>();
        
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            
            colMin.put(y, Math.min(colMin.getOrDefault(y, Integer.MAX_VALUE), x));
            colMax.put(y, Math.max(colMax.getOrDefault(y, Integer.MIN_VALUE), x));
            
            rowMin.put(x, Math.min(rowMin.getOrDefault(x, Integer.MAX_VALUE), y));
            rowMax.put(x, Math.max(rowMax.getOrDefault(x, Integer.MIN_VALUE), y));
        }
        
        int covered = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            
            boolean hasAboveBelow = x > colMin.get(y) && x < colMax.get(y);
            boolean hasLeftRight = y > rowMin.get(x) && y < rowMax.get(x);
            
            if (hasAboveBelow && hasLeftRight) {
                covered++;
            }
        }
        
        return covered;
    }
}
