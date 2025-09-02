import java.util.*;

public class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                // A must be in the upper-left region of B (including same row/col),
                if (!(x1 <= x2 && y1 >= y2)) continue;
                if (x1 == x2 && y1 == y2) continue;

                int xmin = Math.min(x1, x2), xmax = Math.max(x1, x2);
                int ymin = Math.min(y1, y2), ymax = Math.max(y1, y2);

                boolean valid = true;

                if (xmin == xmax) { // vertical line
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j) continue;
                        int px = points[k][0], py = points[k][1];
                        if (px == xmin && py >= ymin && py <= ymax) {
                            valid = false;
                            break;
                        }
                    }
                } else if (ymin == ymax) { // horizontal line
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j) continue;
                        int px = points[k][0], py = points[k][1];
                        if (py == ymin && px >= xmin && px <= xmax) {
                            valid = false;
                            break;
                        }
                    }
                } else { // normal rectangle (border included)
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j) continue;
                        int px = points[k][0], py = points[k][1];
                        if (px >= xmin && px <= xmax && py >= ymin && py <= ymax) {
                            valid = false;
                            break;
                        }
                    }
                }

                if (valid) count++;
            }
        }
        return count;
    }
}
