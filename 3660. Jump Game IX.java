import java.util.*;

class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        DSU dsu = new DSU(n);
        // Map: minimum value in a component -> List of representative indices
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        for (int i = n - 1; i >= 0; i--) {
            int currentMin = nums[i];
            // Get all components to the right that have a min value < current nums[i]
            NavigableMap<Integer, List<Integer>> reachable = map.headMap(nums[i], false);
            
            if (!reachable.isEmpty()) {
                // If i can jump to these components, they all merge with i
                // The new merged component's minimum will be the smallest among them
                currentMin = Math.min(currentMin, reachable.firstKey());
                
                int rootI = dsu.find(i);
                Iterator<List<Integer>> it = reachable.values().iterator();
                while (it.hasNext()) {
                    List<Integer> roots = it.next();
                    for (int r : roots) {
                        dsu.union(rootI, r);
                        rootI = dsu.find(rootI);
                    }
                    it.remove(); // Clear merged components from map
                }
            }
            // Add the updated component back to the map
            map.computeIfAbsent(currentMin, k -> new ArrayList<>()).add(dsu.find(i));
        }

        // Find the maximum value present in each component
        int[] componentMax = new int[n];
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            componentMax[root] = Math.max(componentMax[root], nums[i]);
        }

        // Assign the component's max value to each index
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = componentMax[dsu.find(i)];
        }
        return ans;
    }

    class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) parent[rootI] = rootJ;
        }
    }
}
