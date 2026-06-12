import java.util.*;

class Solution {
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1, LOG = 17;
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] depth = new int[n + 1];
        int[][] up = new int[n + 1][LOG];
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{1, 0});
        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int u = cur[0], p = cur[1];
            up[u][0] = p;
            for (int k = 1; k < LOG; k++)
                up[u][k] = up[ up[u][k-1] ][k-1];
            for (int v : adj[u]) {
                if (v != p) {
                    depth[v] = depth[u] + 1;
                    stack.push(new int[]{v, u});
                }
            }
        }
        int MOD = 1000000007;
        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++)
            pow2[i] = (pow2[i-1] * 2) % MOD;
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            if (u == v) { ans[i] = 0; continue; }
            int w = lca(u, v, depth, up, LOG);
            int L = depth[u] + depth[v] - 2 * depth[w];
            ans[i] = (int) pow2[L-1];
        }
        return ans;
    }

    private int lca(int u, int v, int[] depth, int[][] up, int LOG) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int diff = depth[u] - depth[v];
        for (int k = 0; k < LOG; k++)
            if ((diff & (1 << k)) != 0) u = up[u][k];
        if (u == v) return u;
        for (int k = LOG - 1; k >= 0; k--)
            if (up[u][k] != up[v][k]) { u = up[u][k]; v = up[v][k]; }
        return up[u][0];
    }
}
