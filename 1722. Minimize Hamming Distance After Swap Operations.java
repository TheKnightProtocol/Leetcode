class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        for (int[] swap : allowedSwaps) {
            union(parent, swap[0], swap[1]);
        }
        
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        
        int ans = 0;
        for (List<Integer> indices : groups.values()) {
            Map<Integer, Integer> freq = new HashMap<>();
            for (int i : indices) {
                freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
            }
            for (int i : indices) {
                int val = target[i];
                if (freq.getOrDefault(val, 0) > 0) {
                    freq.put(val, freq.get(val) - 1);
                } else {
                    ans++;
                }
            }
        }
        return ans;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }
    
    private void union(int[] parent, int a, int b) {
        int ra = find(parent, a);
        int rb = find(parent, b);
        if (ra != rb) parent[ra] = rb;
    }
}
