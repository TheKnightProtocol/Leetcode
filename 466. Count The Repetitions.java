public class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) return 0;

        int s1Len = s1.length(), s2Len = s2.length();
        int index2 = 0, count2 = 0;

        // map from index2 in s2 to [iteration number, count2 so far]
        Map<Integer, int[]> seen = new HashMap<>();

        for (int count1 = 0; count1 < n1; count1++) {
            for (int i = 0; i < s1Len; i++) {
                if (s1.charAt(i) == s2.charAt(index2)) {
                    index2++;
                    if (index2 == s2Len) {
                        index2 = 0;
                        count2++;
                    }
                }
            }

            // cycle detection
            if (seen.containsKey(index2)) {
                int[] prev = seen.get(index2);
                int prevCount1 = prev[0];
                int prevCount2 = prev[1];

                int cycleLen = count1 - prevCount1;
                int cycleCount2 = count2 - prevCount2;

                int remainingCycles = (n1 - 1 - count1) / cycleLen;
                count2 += remainingCycles * cycleCount2;
                count1 += remainingCycles * cycleLen;
            } else {
                seen.put(index2, new int[]{count1, count2});
            }
        }

        return count2 / n2;
    }
}
