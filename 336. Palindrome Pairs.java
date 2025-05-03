import java.util.*;

public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // Build map from word to index
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        // Check each word
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                String prefix = word.substring(0, j);
                String suffix = word.substring(j);

                // Case 1: If prefix is palindrome, check for reversed(suffix)
                if (isPalindrome(prefix)) {
                    String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                    if (map.containsKey(reversedSuffix) && map.get(reversedSuffix) != i) {
                        result.add(Arrays.asList(map.get(reversedSuffix), i));
                    }
                }

                // Case 2: If suffix is palindrome, check for reversed(prefix)
                // (Avoid duplicates when suffix is empty)
                if (suffix.length() > 0 && isPalindrome(suffix)) {
                    String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                    if (map.containsKey(reversedPrefix) && map.get(reversedPrefix) != i) {
                        result.add(Arrays.asList(i, map.get(reversedPrefix)));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
