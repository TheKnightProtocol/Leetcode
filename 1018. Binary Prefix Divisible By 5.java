class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        int num = 0;
        
        for (int i = 0; i < nums.length; i++) {
            num = (num * 2 + nums[i]) % 5;
            result.add(num == 0);
        }
        
        return result;
    }
}
