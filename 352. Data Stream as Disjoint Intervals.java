import java.util.*;

public class SummaryRanges {
    private TreeMap<Integer, Integer> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }

    public void addNum(int val) {
        if (intervals.containsKey(val)) return;

        Integer lowerKey = intervals.floorKey(val);
        Integer higherKey = intervals.ceilingKey(val);

        boolean mergeLeft = (lowerKey != null && intervals.get(lowerKey) >= val - 1);
        boolean mergeRight = (higherKey != null && higherKey == val + 1);

        if (mergeLeft && mergeRight) {
            // Merge both sides
            int newStart = lowerKey;
            int newEnd = intervals.get(higherKey);
            intervals.put(newStart, newEnd);
            intervals.remove(higherKey);
        } else if (mergeLeft) {
            // Extend left interval
            intervals.put(lowerKey, Math.max(intervals.get(lowerKey), val));
        } else if (mergeRight) {
            // Extend right interval from the left
            int end = intervals.get(higherKey);
            intervals.remove(higherKey);
            intervals.put(val, end);
        } else {
            // Add new interval
            intervals.put(val, val);
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[intervals.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            res[i][0] = entry.getKey();
            res[i][1] = entry.getValue();
            i++;
        }
        return res;
    }
}
