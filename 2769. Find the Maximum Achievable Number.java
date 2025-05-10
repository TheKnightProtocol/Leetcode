public class Solution {
    public static int theMaximumAchievableX(int num, int t) {
        return num + 2 * t;
    }

    public static void main(String[] args) {
        System.out.println(theMaximumAchievableX(4, 1)); // Output: 6
        System.out.println(theMaximumAchievableX(3, 2)); // Output: 7
    }
}
