public class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int result = check(tops[0], tops, bottoms);
        if (result != -1 || tops[0] == bottoms[0]) {
            return result;
        } else {
            return check(bottoms[0], tops, bottoms);
        }
    }

    private int check(int target, int[] tops, int[] bottoms) {
        int rotateTop = 0;
        int rotateBottom = 0;

        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != target && bottoms[i] != target) {
                return -1; // Cannot make all elements equal to target
            } else if (tops[i] != target) {
                rotateTop++;
            } else if (bottoms[i] != target) {
                rotateBottom++;
            }
        }

        return Math.min(rotateTop, rotateBottom);
    }
}
