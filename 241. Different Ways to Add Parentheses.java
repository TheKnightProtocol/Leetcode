import java.util.*;

class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);

                List<Integer> leftResults = diffWaysToCompute(left);
                List<Integer> rightResults = diffWaysToCompute(right);

                for (int l : leftResults) {
                    for (int r : rightResults) {
                        if (ch == '+') result.add(l + r);
                        else if (ch == '-') result.add(l - r);
                        else if (ch == '*') result.add(l * r);
                    }
                }
            }
        }

        // Base case: input is a single number (no operators)
        if (result.isEmpty()) {
            result.add(Integer.parseInt(input));
        }

        return result;
    }
}
