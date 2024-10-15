import java.util.Stack;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Update heights array
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            // Calculate the maximum area for the current row's histogram
            maxArea = Math.max(maxArea, calculateMaxArea(heights));
        }

        return maxArea;
    }

    private int calculateMaxArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int index = 0;

        while (index < heights.length) {
            // If this bar is higher than the last bar in the stack, push it to the stack
            if (stack.isEmpty() || heights[index] >= heights[stack.peek()]) {
                stack.push(index++);
            } else {
                // Calculate area for the top of the stack
                int top = stack.pop();
                // Calculate the width of the rectangle
                int width = stack.isEmpty() ? index : index - stack.peek() - 1;
                // Calculate the area
                maxArea = Math.max(maxArea, heights[top] * width);
            }
        }

        // Now pop the remaining bars from stack
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int width = stack.isEmpty() ? index : index - stack.peek() - 1;
            maxArea = Math.max(maxArea, heights[top] * width);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test Cases
        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println(solution.maximalRectangle(matrix1)); // Output: 6

        char[][] matrix2 = {
            {'0'}
        };
        System.out.println(solution.maximalRectangle(matrix2)); // Output: 0

        char[][] matrix3 = {
            {'1'}
        };
        System.out.println(solution.maximalRectangle(matrix3)); // Output: 1
    }
}
