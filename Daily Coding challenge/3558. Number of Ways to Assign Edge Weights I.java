import java.util.*;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] depth = new int[n + 1];
        Arrays.fill(depth, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        depth[1] = 0;
        int maxDepth = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (depth[v] == -1) {
                    depth[v] = depth[u] + 1;
                    maxDepth = Math.max(maxDepth, depth[v]);
                    q.offer(v);
                }
            }
        }
        int MOD = 1000000007;
        long ans = 1, base = 2, exp = maxDepth - 1;
        while (exp > 0) {
            if ((exp & 1) == 1) ans = (ans * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) ans;
    }
}
