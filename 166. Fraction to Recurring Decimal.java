import java.util.*;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // Convert to long to handle edge cases
        long num = numerator;
        long den = denominator;
        
        // Handle sign and convert to absolute values
        boolean isNegative = (num < 0) ^ (den < 0);
        num = Math.abs(num);
        den = Math.abs(den);
        
        // Integer part of the quotient
        long integerPart = num / den;
        long remainder = num % den;

        // If no remainder, return the result directly
        if (remainder == 0) {
            return isNegative ? "-" + integerPart : String.valueOf(integerPart);
        }

        // Start building result
        StringBuilder result = new StringBuilder();
        if (isNegative) result.append("-");
        result.append(integerPart).append(".");

        // Map to track remainder positions
        Map<Long, Integer> remainderMap = new HashMap<>();
        
        // Decimal part computation
        while (remainder != 0) {
            // If remainder repeats, insert parentheses
            if (remainderMap.containsKey(remainder)) {
                int startIndex = remainderMap.get(remainder);
                result.insert(startIndex, "(");
                result.append(")");
                break;
            }

            // Store remainder position before appending the next digit
            remainderMap.put(remainder, result.length());

            // Compute next digit
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.fractionToDecimal(1, 2));  // Output: "0.5"
        System.out.println(sol.fractionToDecimal(2, 1));  // Output: "2"
        System.out.println(sol.fractionToDecimal(4, 333));// Output: "0.(012)"
        System.out.println(sol.fractionToDecimal(-50, 8));// Output: "-6.25"
    }
}
