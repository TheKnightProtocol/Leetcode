import java.util.*;

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int max = Integer.MIN_VALUE;

        // Initialize the heap with the first element from each list
        for (int i = 0; i < k; i++) {
            int val = nums.get(i).get(0);
            minHeap.offer(new int[]{val, i, 0}); // {value, list index, index in list}
            max = Math.max(max, val);
        }

        int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;

        while (minHeap.size() == k) {
            int[] curr = minHeap.poll();
            int minVal = curr[0], row = curr[1], idx = curr[2];

            if (max - minVal < rangeEnd - rangeStart ||
                (max - minVal == rangeEnd - rangeStart && minVal < rangeStart)) {
                rangeStart = minVal;
                rangeEnd = max;
            }

            if (idx + 1 < nums.get(row).size()) {
                int nextVal = nums.get(row).get(idx + 1);
                minHeap.offer(new int[]{nextVal, row, idx + 1});
                max = Math.max(max, nextVal);
            } else {
                break; // One of the lists is exhausted
            }
        }

        return new int[]{rangeStart, rangeEnd};
    }
}
