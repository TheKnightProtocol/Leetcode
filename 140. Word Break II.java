import java.util.*;

public class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, List<String>> memo = new HashMap<>();
        return backtrack(s, wordSet, memo);
    }

    private List<String> backtrack(String s, Set<String> wordSet, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        if (s.isEmpty()) return Arrays.asList("");

        List<String> res = new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordSet.contains(prefix)) {
                List<String> suffixWays = backtrack(s.substring(i), wordSet, memo);
                for (String sentence : suffixWays) {
                    res.add(prefix + (sentence.isEmpty() ? "" : " " + sentence));
                }
            }
        }
        memo.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        String s1 = "catsanddog";
        List<String> wordDict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(obj.wordBreak(s1, wordDict1));

        String s2 = "pineapplepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(obj.wordBreak(s2, wordDict2));

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(obj.wordBreak(s3, wordDict3));
    }
}
