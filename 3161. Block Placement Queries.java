import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int[] treeMin, treeMax, treeGap;
    final int INF = 1_000_000_000;

    public List<Boolean> getResults(int[][] queries) {
        int n = 0;
        for (int[] q : queries) {
            n = Math.max(n, q[1]);
        }

        treeMin = new int[4 * (n + 1)];
        treeMax = new int[4 * (n + 1)];
        treeGap = new int[4 * (n + 1)];

        Arrays.fill(treeMin, INF);
        Arrays.fill(treeMax, -INF);

        List<Boolean> res = new ArrayList<>();

        for (int[] q : queries) {
            if (q[0] == 1) {
                update(1, 0, n, q[1]);
            } else {
                int x = q[1];
                int sz = q[2];
                int[] qRes = query(1, 0, n, 0, x);
                
                int minV = qRes[0];
                int maxV = qRes[1];
                int maxGap = qRes[2];

                int maxSpace;
                if (minV == INF) {
                    maxSpace = x;
                } else {
                    maxSpace = Math.max(maxGap, Math.max(minV, x - maxV));
                }
                
                res.add(maxSpace >= sz);
            }
        }
        return res;
    }

    private void update(int node, int l, int r, int pos) {
        if (l == r) {
            treeMin[node] = pos;
            treeMax[node] = pos;
            treeGap[node] = 0;
            return;
        }
        
        int mid = l + (r - l) / 2;
        if (pos <= mid) {
            update(2 * node, l, mid, pos);
        } else {
            update(2 * node + 1, mid + 1, r, pos);
        }
        
        treeMin[node] = Math.min(treeMin[2 * node], treeMin[2 * node + 1]);
        treeMax[node] = Math.max(treeMax[2 * node], treeMax[2 * node + 1]);
        treeGap[node] = Math.max(treeGap[2 * node], treeGap[2 * node + 1]);
        
        if (treeMax[2 * node] != -INF && treeMin[2 * node + 1] != INF) {
            treeGap[node] = Math.max(treeGap[node], treeMin[2 * node + 1] - treeMax[2 * node]);
        }
    }

    private int[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return new int[]{treeMin[node], treeMax[node], treeGap[node]};
        }
        
        int mid = l + (r - l) / 2;
        
        if (qr <= mid) {
            return query(2 * node, l, mid, ql, qr);
        } else if (ql > mid) {
            return query(2 * node + 1, mid + 1, r, ql, qr);
        } else {
            int[] left = query(2 * node, l, mid, ql, qr);
            int[] right = query(2 * node + 1, mid + 1, r, ql, qr);
            
            int minV = Math.min(left[0], right[0]);
            int maxV = Math.max(left[1], right[1]);
            int gap = Math.max(left[2], right[2]);
            
            if (left[1] != -INF && right[0] != INF) {
                gap = Math.max(gap, right[0] - left[1]);
            }
            
            return new int[]{minV, maxV, gap};
        }
    }
}
