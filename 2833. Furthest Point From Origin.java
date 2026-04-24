class Solution {
    public int furthestDistanceFromOrigin(String moves) {
     int lCount = 0;
     int rCount = 0;
     int wildCards = 0;
     for (char c : moves.toCharArray()) {
        if (c == 'L') lCount++;
        else if (c == 'R') rCount++;
        else wildCards++;
     }   
     return Math.abs(lCount - rCount) + wildCards;
    }
}
