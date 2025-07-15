import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> results = new ArrayList<>();

        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                int bitsH = countSetBits(h);
                int bitsM = countSetBits(m);

                if (bitsH + bitsM == turnedOn) {
                    // Formats the minute to always be two digits (e.g., 5 becomes "05")
                    String formattedMinute = String.format("%02d", m);
                    String timeString = h + ":" + formattedMinute;
                    results.add(timeString);
                }
            }
        }

        return results;
    }

    // Helper method to count the number of set bits (1s) in an integer's binary representation
    private int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1); // This clever trick clears the least significant set bit
            count++;
        }
        return count;
    }
}
