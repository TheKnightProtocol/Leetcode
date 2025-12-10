import java.util.*;

class Solution {
    static final int MOD = 1000000007;
    
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        
        boolean[] canBeUnlocked = new boolean[n];
        canBeUnlocked[0] = true;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (canBeUnlocked[j] && complexity[j] < complexity[i]) {
                    canBeUnlocked[i] = true;
                    break;
                }
            }
            if (!canBeUnlocked[i]) return 0;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(i);
        
        Collections.sort(list, (a, b) -> {
            if (complexity[a] != complexity[b]) {
                return Integer.compare(complexity[a], complexity[b]);
            }
            return Integer.compare(a, b);
        });
        
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[list.get(i)] = i;
        }
        
        if (pos[0] != 0) return 0;
        
        FenwickTree ft = new FenwickTree(n);
        ft.update(pos[0] + 1, 1);
        
        long result = 1;
        int total = 1;
        
        for (int i = 1; i < n; i++) {
            int count = ft.query(pos[i]);
            if (count == 0) return 0;
            result = result * total % MOD;
            ft.update(pos[i] + 1, 1);
            total++;
        }
        
        return (int) result;
    }
    
    class FenwickTree {
        int[] bit;
        int n;
        
        FenwickTree(int n) {
            this.n = n;
            bit = new int[n + 1];
        }
        
        void update(int idx, int val) {
            while (idx <= n) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }
        
        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
}
