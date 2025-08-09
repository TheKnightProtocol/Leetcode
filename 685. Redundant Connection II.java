class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] cand1 = null, cand2 = null;

        // Step 1: Find a node with two parents
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                cand1 = new int[]{parent[v], v};
                cand2 = new int[]{u, v};
                edge[1] = 0; // mark this edge as invalid temporarily
            }
        }

        // Step 2: Union-Find to detect cycle
        int[] uf = new int[n + 1];
        for (int i = 1; i <= n; i++) uf[i] = i;

        for (int[] edge : edges) {
            if (edge[1] == 0) continue; // skip invalid edge
            int u = edge[0], v = edge[1];
            int pu = find(uf, u), pv = find(uf, v);
            if (pu == pv) {
                if (cand1 != null) return cand1;
                return edge;
            }
            uf[pv] = pu;
        }
        return cand2;
    }

    private int find(int[] uf, int x) {
        if (uf[x] != x) uf[x] = find(uf, uf[x]);
        return uf[x];
    }
}
