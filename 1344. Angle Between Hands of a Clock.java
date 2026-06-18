class Solution {
    public double angleClock(int hour, int minutes) {
        double hourAngle = (hour % 12) * 30.
        0 + minutes * 0.5;
        double minAngle = minutes * 6.0;
        double diff = Math.abs(hourAngle - minAngle);
        return Math.min(diff, 360.0 - diff);
    }
}
