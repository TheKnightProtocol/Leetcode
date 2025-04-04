import java.util.*;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';  // default sign before any number

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If current char is digit, build the number
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            // If current char is operator or end of string, do operation
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num); // truncates toward zero
                        break;
                }
                sign = c;  // update current sign
                num = 0;   // reset current number
            }
        }

        // Sum up all results in the stack
        int result = 0;
        for (int n : stack) result += n;

        return result;
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.calculate("3+2*2"));    // Output: 7
        System.out.println(sol.calculate(" 3/2 "));    // Output: 1
        System.out.println(sol.calculate(" 3+5 / 2 "));// Output: 5
    }
}
