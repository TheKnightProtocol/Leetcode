import java.util.*;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        for (int[] c : classes) {
            pq.offer(new double[]{gain(c[0], c[1]), c[0], c[1]});
        }
        while (extraStudents-- > 0) {
            double[] cur = pq.poll();
            int pass = (int) cur[1] + 1;
            int total = (int) cur[2] + 1;
            pq.offer(new double[]{gain(pass, total), pass, total});
        }
        double sum = 0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            sum += cur[1] / cur[2];
        }
        return sum / classes.length;
    }

    private double gain(int pass, int total) {
        return (double)(pass + 1) / (total + 1) - (double) pass / total;
    }
}
