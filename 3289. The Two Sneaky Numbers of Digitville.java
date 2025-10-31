class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        int[] result = new int[2];
        int index = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int absVal = Math.abs(nums[i]);
            if (nums[absVal] < 0) {
                result[index++] = absVal;
            } else {
                nums[absVal] = -nums[absVal];
            }
        }
        
        return result;
    }
}
