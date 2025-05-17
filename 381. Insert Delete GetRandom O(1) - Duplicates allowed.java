import java.util.*;

class RandomizedCollection {
    private List<Integer> nums;
    private Map<Integer, Set<Integer>> idxMap;
    private Random rand;

    public RandomizedCollection() {
        nums = new ArrayList<>();
        idxMap = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        boolean notPresent = !idxMap.containsKey(val);
        idxMap.putIfAbsent(val, new HashSet<>());
        idxMap.get(val).add(nums.size());
        nums.add(val);
        return notPresent;
    }

    public boolean remove(int val) {
        if (!idxMap.containsKey(val) || idxMap.get(val).isEmpty()) {
            return false;
        }

        // Get an index of the element to remove
        int removeIdx = idxMap.get(val).iterator().next();
        idxMap.get(val).remove(removeIdx);

        int lastIdx = nums.size() - 1;
        int lastVal = nums.get(lastIdx);

        // Move lastVal into the place of removeIdx
        nums.set(removeIdx, lastVal);
        if (removeIdx != lastIdx) {
            idxMap.get(lastVal).remove(lastIdx);
            idxMap.get(lastVal).add(removeIdx);
        }

        // Remove last element
        nums.remove(lastIdx);

        if (idxMap.get(val).isEmpty()) {
            idxMap.remove(val);
        }

        return true;
    }

    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
