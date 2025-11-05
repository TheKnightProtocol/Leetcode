import java.util.*;

class Solution {
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] result = new long[n - k + 1];
        
        Map<Integer, Integer> freqMap = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int freqA = freqMap.get(a);
            int freqB = freqMap.get(b);
            if (freqA != freqB) {
                return freqB - freqA;
            }
            return b - a;
        });
        
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            
            // Update frequency
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            
            // Remove old element if window is moving
            if (i >= k) {
                int removeNum = nums[i - k];
                freqMap.put(removeNum, freqMap.get(removeNum) - 1);
                if (freqMap.get(removeNum) == 0) {
                    freqMap.remove(removeNum);
                }
            }
            
            // Clear and rebuild priority queue
            pq.clear();
            for (int key : freqMap.keySet()) {
                pq.offer(key);
            }
            
            if (i >= k - 1) {
                long sum = 0;
                int count = 0;
                List<Integer> temp = new ArrayList<>();
                
                while (!pq.isEmpty() && count < x) {
                    int val = pq.poll();
                    temp.add(val);
                    sum += (long) val * freqMap.get(val);
                    count++;
                }
                
                // Add back to pq for next iteration
                pq.addAll(temp);
                result[i - k + 1] = sum;
            }
        }
        
        return result;
    }
}
