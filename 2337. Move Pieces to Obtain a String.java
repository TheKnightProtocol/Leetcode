public class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;

        while (i < n || j < n) {
            // Skip underscores
            while (i < n && start.charAt(i) == '_') i++;
            while (j < n && target.charAt(j) == '_') j++;

            // If both reach the end, we are done
            if (i == n && j == n) return true;
            // If only one reaches the end, mismatch
            if (i == n || j == n) return false;

            char c1 = start.charAt(i);
            char c2 = target.charAt(j);

            // Mismatched characters
            if (c1 != c2) return false;

            // 'L' can't move right
            if (c1 == 'L' && j > i) return false;
            // 'R' can't move left
            if (c1 == 'R' && j < i) return false;

            i++;
            j++;
        }

        return true;
    }
}
