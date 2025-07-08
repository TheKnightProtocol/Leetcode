import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort the intervals by their start times
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(intervals[0]); // Add the first interval

        for (int i = 1; i < intervals.length; i++) {
            int[] lastMerged = mergedIntervals.get(mergedIntervals.size() - 1);
            int[] current = intervals[i];

            // If the current interval overlaps with the last merged interval
            if (current[0] <= lastMerged[1]) {
                // Merge them by extending the end of the last merged interval
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // No overlap, add the current interval as a new one
                mergedIntervals.add(current);
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
