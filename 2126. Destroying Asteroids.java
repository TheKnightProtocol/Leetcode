import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        long currentMass = mass;
        Arrays.sort(asteroids);
        for (int a : asteroids) {
            if (currentMass < a) return false;
            currentMass += a;
        }
        return true;
    }
}
