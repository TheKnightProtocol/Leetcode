public class Solution {
    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", 
                                          "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", 
                                          "Seventeen", "Eighteen", "Nineteen"};
    
    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String result = "";
        int index = 0;

        while (num > 0) {
            int cur = num % 1000;
            if (cur != 0) {
                result = helper(cur) + thousands[index] + " " + result;
            }
            num /= 1000;
            index++;
        }

        return result.trim();
    }

    private String helper(int num) {
        if (num == 0) return "";
        else if (num < 20) return belowTwenty[num] + " ";
        else if (num < 100) return tens[num / 10] + " " + helper(num % 10);
        else return belowTwenty[num / 100] + " Hundred " + helper(num % 100);
    }
}
