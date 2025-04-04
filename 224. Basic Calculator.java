import java.util.*;

public class Solution { // Changed class name to Solution
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0, number = 0, sign = 1; // Default sign is positive

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0'); // Build the number
            } else if (c == '+') {
                result += sign * number; // Add the previous number to result
                number = 0;
                sign = 1; // Reset sign to positive
            } else if (c == '-') {
                result += sign * number; // Add the previous number to result
                number = 0;
                sign = -1; // Reset sign to negative
            } else if (c == '(') {
                stack.push(result); // Store current result
                stack.push(sign); // Store current sign
                result = 0; // Reset result
                sign = 1; // Reset sign to positive
            } else if (c == ')') {
                result += sign * number; // Add the last number before ')'
                number = 0;
                result *= stack.pop(); // Apply sign before '('
                result += stack.pop(); // Add previous result before '('
            }
        }

        return result + (sign * number); // Add the last number in expression
    }

    public static void main(String[] args) {
        Solution calculator = new Solution(); // Class name changed to Solution
        System.out.println(calculator.calculate("1 + 1")); // Output: 2
        System.out.println(calculator.calculate(" 2-1 + 2 ")); // Output: 3
        System.out.println(calculator.calculate("(1+(4+5+2)-3)+(6+8)")); // Output: 23
    }
}
