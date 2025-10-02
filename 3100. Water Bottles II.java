class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int totalDrunk = 0;
        int emptyBottles = 0;
        int currentExchange = numExchange;
        
        while (numBottles > 0 || emptyBottles >= currentExchange) {
            if (numBottles > 0) {
                // Drink all full bottles
                totalDrunk += numBottles;
                emptyBottles += numBottles;
                numBottles = 0;
            } else if (emptyBottles >= currentExchange) {
                // Exchange exactly currentExchange empty bottles for 1 full bottle
                emptyBottles -= currentExchange;
                numBottles = 1;
                currentExchange++;
            }
        }
        
        return totalDrunk;
    }
}
