import java.util.*;

class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]); // sort by lastDay
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;

        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];
            time += duration;
            maxHeap.offer(duration);

            if (time > lastDay) {
                time -= maxHeap.poll(); // drop the longest course to meet deadline
            }
        }

        return maxHeap.size();
    }
}
