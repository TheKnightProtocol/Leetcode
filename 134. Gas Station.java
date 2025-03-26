public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, totalCost = 0;
        int currentTank = 0, startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentTank += gas[i] - cost[i];

            // If the tank is empty at this station, reset startIndex
            if (currentTank < 0) {
                startIndex = i + 1;
                currentTank = 0;
            }
        }

        return totalGas >= totalCost ? startIndex : -1;
    }
}
