import java.util.*;

class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        
        for (int i = 0; i <= n - k; i++) {
            int[] subarray = Arrays.copyOfRange(nums, i, i + k);
            result[i] = calculateXSum(subarray, x);
        }
        
        return result;
    }
    
    private int calculateXSum(int[] arr, int x) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        
        if (frequency.size() <= x) {
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            return sum;
        }
        
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(frequency.entrySet());
        Collections.sort(entries, (a, b) -> {
            if (a.getValue().equals(b.getValue())) {
                return b.getKey() - a.getKey();
            }
            return b.getValue() - a.getValue();
        });
        
        Set<Integer> topX = new HashSet<>();
        for (int i = 0; i < x; i++) {
            topX.add(entries.get(i).getKey());
        }
        
        int sum = 0;
        for (int num : arr) {
            if (topX.contains(num)) {
                sum += num;
            }
        }
        
        return sum;
    }
}
