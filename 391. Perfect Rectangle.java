import java.util.*;

public class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        Set<String> corners = new HashSet<>();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        int totalArea = 0;

        for (int[] rect : rectangles) {
            int x1 = rect[0], y1 = rect[1];
            int x2 = rect[2], y2 = rect[3];

            // Total area
            totalArea += (x2 - x1) * (y2 - y1);

            // Update bounding rectangle
            minX = Math.min(minX, x1);
            minY = Math.min(minY, y1);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);

            // Check 4 corners
            String[] points = {
                x1 + " " + y1,
                x1 + " " + y2,
                x2 + " " + y1,
                x2 + " " + y2
            };

            for (String point : points) {
                if (!corners.add(point)) {
                    corners.remove(point); // cancel out if seen before
                }
            }
        }

        // Check area
        int expectedArea = (maxX - minX) * (maxY - minY);
        if (totalArea != expectedArea) return false;

        // Should have exactly 4 corners left
        if (corners.size() != 4 ||
            !corners.contains(minX + " " + minY) ||
            !corners.contains(minX + " " + maxY) ||
            !corners.contains(maxX + " " + minY) ||
            !corners.contains(maxX + " " + maxY)) {
            return false;
        }

        return true;
    }
}
