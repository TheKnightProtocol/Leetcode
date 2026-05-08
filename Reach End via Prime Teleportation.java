import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int maxVal = 0;
        for (int v : nums) if (v > maxVal) maxVal = v;

        // smallest prime factor sieve
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == 0) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) spf[i] = i;
        }

        boolean[] isPrime = new boolean[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == i) isPrime[i] = true;
        }

        // map each prime to list of indices whose value is divisible by that prime
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int last = 0;
            while (x > 1) {
                int p = spf[x];
                if (p != last) {
                    primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                    last = p;
                }
                x /= p;
            }
        }

        // BFS
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        boolean[] primeUsed = new boolean[maxVal + 1];
        Queue<Integer> q = new ArrayDeque<>();
        visited[0] = true;
        dist[0] = 0;
        q.add(0);

        while (!q.isEmpty()) {
            int i = q.poll();
            int d = dist[i];
            if (i == n - 1) return d;

            // adjacent moves
            if (i > 0 && !visited[i - 1]) {
                visited[i - 1] = true;
                dist[i - 1] = d + 1;
                q.add(i - 1);
            }
            if (i + 1 < n && !visited[i + 1]) {
                visited[i + 1] = true;
                dist[i + 1] = d + 1;
                q.add(i + 1);
            }

            // teleportation if current value is prime
            int val = nums[i];
            if (val > 1 && isPrime[val] && !primeUsed[val]) {
                primeUsed[val] = true;
                List<Integer> targets = primeToIndices.get(val);
                if (targets != null) {
                    for (int j : targets) {
                        if (!visited[j]) {
                            visited[j] = true;
                            dist[j] = d + 1;
                            q.add(j);
                        }
                    }
                }
            }
        }
        return dist[n - 1];
    }
}
