public class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        int len = n.length();
        Set<Long> candidates = new HashSet<>();

        // Edge cases: 10^len + 1 and 10^(len - 1) - 1
        candidates.add((long)Math.pow(10, len) + 1);
        candidates.add((long)Math.pow(10, len - 1) - 1);

        // Get the prefix (first half)
        long prefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = prefix - 1; i <= prefix + 1; i++) {
            StringBuilder sb = new StringBuilder();
            String firstHalf = String.valueOf(i);
            sb.append(firstHalf);

            // Mirror the prefix
            String secondHalf = new StringBuilder(firstHalf.substring(0, len / 2)).reverse().toString();
            sb.append(secondHalf);

            try {
                long cand = Long.parseLong(sb.toString());
                candidates.add(cand);
            } catch (NumberFormatException e) {
                // Ignore overflow
            }
        }

        // Compare all candidates
        long minDiff = Long.MAX_VALUE;
        long res = -1;
        for (long cand : candidates) {
            if (cand == num) continue;
            long diff = Math.abs(cand - num);
            if (diff < minDiff || (diff == minDiff && cand < res)) {
                minDiff = diff;
                res = cand;
            }
        }

        return String.valueOf(res);
    }
}
