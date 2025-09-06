import java.util.*;

class Solution {
    public long minOperationsSum(int[][] queries) {
        long totalOperations = 0;
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            totalOperations += calculateOperations(l, r);
        }
        return totalOperations;
    }
    
    private long calculateOperations(int l, int r) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num = l; num <= r; num++) {
            int temp = num;
            while (temp > 0) {
                freq.put(temp, freq.getOrDefault(temp, 0) + 1);
                temp /= 4;
            }
        }
        
        long operations = 0;
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getKey() - a.getKey()
        );
        pq.addAll(freq.entrySet());
        
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            int num = entry.getKey();
            int count = entry.getValue();
            
            if (num == 0) continue;
            
            int operationsNeeded = (count + 1) / 2;
            operations += operationsNeeded;
            
            int parent = num / 4;
            if (parent > 0) {
                freq.put(parent, freq.getOrDefault(parent, 0) + operationsNeeded);
                pq.offer(new AbstractMap.SimpleEntry<>(parent, freq.get(parent)));
            }
        }
        
        return operations;
    }
}
