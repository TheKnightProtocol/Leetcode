import java.util.*;

public class Solution {  // Renamed from MaxPointsOnLine to Solution
    public int maxPoints(int[][] points) {
        if (points.length <= 1) {
            return points.length;
        }
        
        int maxCount = 1;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int samePoint = 0;
            int localMax = 0;

            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;

                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                int dx = x2 - x1;
                int dy = y2 - y1;

                if (dx == 0 && dy == 0) {
                    samePoint++;
                    continue;
                }

                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                String slope = dy + "/" + dx;

                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
                localMax = Math.max(localMax, slopeCount.get(slope));
            }

            maxCount = Math.max(maxCount, localMax + samePoint + 1);
        }

        return maxCount;
    }

    private int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();  // Match class name here
        System.out.println(solution.maxPoints(new int[][]{{1,1}, {2,2}, {3,3}})); // Output: 3
        System.out.println(solution.maxPoints(new int[][]{{1,1}, {3,2}, {5,3}, {4,1}, {2,3}, {1,4}})); // Output: 4
    }
}
