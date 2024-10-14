import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        if (target < 0) {
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            // Add the candidate and recurse
            currentCombination.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, currentCombination, result);
            // Backtrack
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println(solution.combinationSum(candidates1, target1)); // Expected Output: [[2, 2, 3], [7]]

        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println(solution.combinationSum(candidates2, target2)); // Expected Output: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]

        int[] candidates3 = {2};
        int target3 = 1;
        System.out.println(solution.combinationSum(candidates3, target3)); // Expected Output: []
    }
}
