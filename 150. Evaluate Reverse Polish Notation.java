import java.util.*;

public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = stack.pop();  // Second operand (top)
                int a = stack.pop();  // First operand (next top)
                stack.push(applyOperation(a, b, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int applyOperation(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b; // Integer division truncates toward zero
        }
        throw new IllegalArgumentException("Invalid operator: " + operator);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.evalRPN(new String[]{"2", "1", "+", "3", "*"})); // Output: 9
        System.out.println(solution.evalRPN(new String[]{"4", "13", "5", "/", "+"})); // Output: 6
        System.out.println(solution.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"})); // Output: 22
    }
}
