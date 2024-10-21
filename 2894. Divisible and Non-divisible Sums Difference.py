class Solution {
    // Rename the method to match the driver code
    public int differenceOfSums(int n, int m) {  
        int num1 = 0;  // Sum of numbers not divisible by m
        int num2 = 0;  // Sum of numbers divisible by m
        
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                num2 += i;  // Add to num2 if divisible by m
            } else {
                num1 += i;  // Add to num1 if not divisible by m
            }
        }
        
        return num1 - num2;  // Return the difference
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test case 1
        System.out.println(solution.differenceOfSums(10, 3));  // Output: 19
        
        // Test case 2
        System.out.println(solution.differenceOfSums(5, 6));   // Output: 15
        
        // Test case 3
        System.out.println(solution.differenceOfSums(5, 1));   // Output: -15
    }
}
