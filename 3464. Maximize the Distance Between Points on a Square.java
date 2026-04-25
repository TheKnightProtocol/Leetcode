import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] d = new long[n];
        long perimeter = 4L * side;

        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (y == 0) d[i] = x;
            else if (x == side) d[i] = (long) side + y;
            else if (y == side) d[i] = 2L * side + (side - x);
            else d[i] = 3L * side + (side - y);
        }

        Arrays.sort(d);

        long low = 1, high = perimeter / k;
        long ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canPlace(d, mid, k, perimeter)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (int) ans;
    }

    private boolean canPlace(long[] d, long minDist, int k, long perimeter) {
        int n = d.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && d[i] > d[0] + minDist) break;

            int count = 1;
            long lastPos = d[i];
            
            for (int j = 1; j < k; j++) {
                long target = lastPos + minDist;
                int nextIdx = lowerBound(d, target);
                if (nextIdx >= n) {
                    count = -1;
                    break;
                }
                lastPos = d[nextIdx];
                count++;
            }

            if (count == k && (d[i] + perimeter - lastPos) >= minDist) {
                return true;
            }
        }
        return false;
    }

    private int lowerBound(long[] d, long target) {
        int l = 0, r = d.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (d[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
