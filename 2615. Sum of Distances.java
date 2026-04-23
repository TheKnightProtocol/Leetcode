import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> groups = new HashMap<>();
        
        for (int i = 0; i < n; i++) 
            groups.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);

        for (List<Integer> list : groups.values()) {
            long sum = 0;
            for (int x : list) sum += x;
            
            long pre = 0;
            for (int i = 0; i < list.size(); i++) {
                long val = list.get(i);
                res[(int)val] = (val * i - pre) + (sum - pre - val - val * (list.size() - 1 - i));
                pre += val;
            }
        }
        return res;
    }
}
