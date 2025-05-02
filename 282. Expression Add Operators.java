import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(num, target, 0, 0, 0, "", result);
        return result;
    }

    private void backtrack(String num, int target, int index, long prevOperand, long currentOperand, String expression, List<String> result) {
        if (index == num.length()) {
            if (currentOperand == target) {
                result.add(expression);
            }
            return;
        }

        for (int i = index + 1; i <= num.length(); i++) {
            String tempStr = num.substring(index, i);
            if (tempStr.length() > 1 && tempStr.charAt(0) == '0') {
                continue;  // Skip numbers with leading zeros
            }
            long currNum = Long.parseLong(tempStr);

            if (index == 0) {
                // For the first number, there is no operator before it.
                backtrack(num, target, i, currNum, currNum, tempStr, result);
            } else {
                // Add operator "+"
                backtrack(num, target, i, currNum, currentOperand + currNum, expression + "+" + tempStr, result);
                // Add operator "-"
                backtrack(num, target, i, -currNum, currentOperand - currNum, expression + "-" + tempStr, result);
                // Add operator "*"
                backtrack(num, target, i, prevOperand * currNum, currentOperand - prevOperand + (prevOperand * currNum), expression + "*" + tempStr, result);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addOperators("123", 6)); // ["1*2*3", "1+2+3"]
        System.out.println(solution.addOperators("232", 8)); // ["2*3+2", "2+3*2"]
        System.out.println(solution.addOperators("3456237490", 9191)); // []
    }
}
