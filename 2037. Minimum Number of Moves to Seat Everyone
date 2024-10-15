import java.util.Arrays;

public class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        // Step 1: Sort both the seats and students arrays
        Arrays.sort(seats);
        Arrays.sort(students);
        
        int totalMoves = 0;
        
        // Step 2: Calculate the total moves required
        for (int i = 0; i < seats.length; i++) {
            totalMoves += Math.abs(seats[i] - students[i]);
        }
        
        return totalMoves; // Return the total moves
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example test case 1
        int[] seats1 = {3, 1, 5};
        int[] students1 = {2, 7, 4};
        System.out.println(solution.minMovesToSeat(seats1, students1)); // Output: 4

        // Example test case 2
        int[] seats2 = {4, 1, 5, 9};
        int[] students2 = {1, 3, 2, 6};
        System.out.println(solution.minMovesToSeat(seats2, students2)); // Output: 7

        // Example test case 3
        int[] seats3 = {2, 2, 6, 6};
        int[] students3 = {1, 3, 2, 6};
        System.out.println(solution.minMovesToSeat(seats3, students3)); // Output: 4
    }
}
