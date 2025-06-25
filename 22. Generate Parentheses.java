import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<String> result; // Stores all valid combinations

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>(); // Initialize the list
        // Start the backtracking process
        // current: the string built so far
        // openCount: number of '(' added
        // closeCount: number of ')' added
        // maxPairs: the total number of pairs (n)
        backtrack(new StringBuilder(), 0, 0, n);
        return result;
    }

    /**
     * Recursive helper function to build combinations using backtracking.
     *
     * @param current The current combination string being built.
     * @param openCount The count of open parentheses added so far.
     * @param closeCount The count of close parentheses added so far.
     * @param maxPairs The maximum number of pairs (n).
     */
    private void backtrack(StringBuilder current, int openCount, int closeCount, int maxPairs) {
        // Base case: If the current string's length is twice the maxPairs,
        // it means we have placed all (n*2) parentheses.
        // This combination is valid, so add it to the result list.
        if (current.length() == maxPairs * 2) {
            result.add(current.toString());
            return; // Backtrack to the previous state
        }

        // Rule 1: We can add an opening parenthesis '(' if the number of
        // open parentheses added so far is less than the maximum allowed (maxPairs).
        if (openCount < maxPairs) {
            current.append('('); // Add '('
            backtrack(current, openCount + 1, closeCount, maxPairs); // Recurse
            current.deleteCharAt(current.length() - 1); // Backtrack: remove '('
        }

        // Rule 2: We can add a closing parenthesis ')' if the number of
        // closing parentheses added so far is less than the number of
        // open parentheses added so far. This ensures well-formedness.
        if (closeCount < openCount) {
            current.append(')'); // Add ')'
            backtrack(current, openCount, closeCount + 1, maxPairs); // Recurse
            current.deleteCharAt(current.length() - 1); // Backtrack: remove ')'
        }
    }
}
