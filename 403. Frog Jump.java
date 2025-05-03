import java.util.*;

public class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }

        map.get(0).add(0); // Starting jump is 0 (we simulate jump from 0 -> 1 with +1)

        for (int stone : stones) {
            for (int jump : map.get(stone)) {
                for (int step = jump - 1; step <= jump + 1; step++) {
                    if (step > 0 && map.containsKey(stone + step)) {
                        map.get(stone + step).add(step);
                    }
                }
            }
        }

        return !map.get(stones[stones.length - 1]).isEmpty();
    }
}
