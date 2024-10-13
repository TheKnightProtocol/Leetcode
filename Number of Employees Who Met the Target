public class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int count = 0;
        for (int hour : hours) {
            if (hour >= target) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] hours1 = {0, 1, 2, 3, 4};
        int target1 = 2;
        System.out.println(solution.numberOfEmployeesWhoMetTarget(hours1, target1)); // Output: 3
        
        int[] hours2 = {5, 1, 4, 2, 2};
        int target2 = 6;
        System.out.println(solution.numberOfEmployeesWhoMetTarget(hours2, target2)); // Output: 0
    }
}
 {
