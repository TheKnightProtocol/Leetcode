import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];
            
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test case 1
        int[] heights1 = {2,1,5,6,2,3};
        System.out.println(sol.largestRectangleArea(heights1)); // Output: 10
        
        // Test case 2
        int[] heights2 = {2,4};
        System.out.println(sol.largestRectangleArea(heights2)); // Output: 4
    }
}
