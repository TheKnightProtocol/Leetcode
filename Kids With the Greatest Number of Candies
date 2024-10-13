import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;

        // Find the maximum candies any kid has
        for (int candy : candies) {
            maxCandies = Math.max(maxCandies, candy);
        }

        List<Boolean> result = new ArrayList<>();

        // Determine if each kid can have the greatest number of candies
        for (int i = 0; i < candies.length; i++) {
            result.add(candies[i] + extraCandies >= maxCandies);
        }

        return result; // Return the List<Boolean>
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example test case 1
        int[] candies1 = {2, 3, 5, 1, 3};
        int extraCandies1 = 3;
        List<Boolean> result1 = solution.kidsWithCandies(candies1, extraCandies1);
        System.out.println(result1); // Output: [true, true, true, false, true]

        // Example test case 2
        int[] candies2 = {4, 2, 1, 1, 2};
        int extraCandies2 = 1;
        List<Boolean> result2 = solution.kidsWithCandies(candies2, extraCandies2);
        System.out.println(result2); // Output: [true, false, false, false, false]

        // Example test case 3
        int[] candies3 = {12, 1, 12};
        int extraCandies3 = 10;
        List<Boolean> result3 = solution.kidsWithCandies(candies3, extraCandies3);
        System.out.println(result3); // Output: [true, false, true]
    }
}
