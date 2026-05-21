import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixes = new HashSet<>();
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }
        int maxLen = 0;
        for (int num : arr2) {
            while (num > 0) {
                if (prefixes.contains(num)) {
                    int len = String.valueOf(num).length();
                    if (len > maxLen) maxLen = len;
                    break;
                }
                num /= 10;
            }
        }
        return maxLen;
    }
}
