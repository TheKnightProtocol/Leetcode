public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int h = n - mid;

            if (citations[mid] == h) {
                return h; // Found exact h-index
            } else if (citations[mid] < h) {
                left = mid + 1; // Too small, move right
            } else {
                right = mid - 1; // Too big, move left
            }
        }

        return n - left; // h-index is n - left after loop
    }
}
