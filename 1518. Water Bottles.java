class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalDrunk = 0;
        int emptyBottles = 0;
        int fullBottles = numBottles;
        
        while (fullBottles > 0) {
            // Drink all full bottles
            totalDrunk += fullBottles;
            emptyBottles += fullBottles;
            fullBottles = 0;
            
            // Exchange empty bottles for full ones
            if (emptyBottles >= numExchange) {
                fullBottles = emptyBottles / numExchange;
                emptyBottles = emptyBottles % numExchange;
            }
        }
        
        return totalDrunk;
    }
}
