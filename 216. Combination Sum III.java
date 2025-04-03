import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> temp, int k, int remaining, int start) {
        // Base case: If k elements are selected and sum is n, add to result
        if (temp.size() == k && remaining == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        // Explore numbers from 'start' to '9'
        for (int i = start; i <= 9; i++) {
            if (remaining - i < 0) break;  // Pruning: Stop if sum exceeds target
            temp.add(i); // Choose the number
            backtrack(result, temp, k, remaining - i, i + 1); // Move to next number
            temp.remove(temp.size() - 1); // Undo the choice (Backtrack)
        }
    }
}
