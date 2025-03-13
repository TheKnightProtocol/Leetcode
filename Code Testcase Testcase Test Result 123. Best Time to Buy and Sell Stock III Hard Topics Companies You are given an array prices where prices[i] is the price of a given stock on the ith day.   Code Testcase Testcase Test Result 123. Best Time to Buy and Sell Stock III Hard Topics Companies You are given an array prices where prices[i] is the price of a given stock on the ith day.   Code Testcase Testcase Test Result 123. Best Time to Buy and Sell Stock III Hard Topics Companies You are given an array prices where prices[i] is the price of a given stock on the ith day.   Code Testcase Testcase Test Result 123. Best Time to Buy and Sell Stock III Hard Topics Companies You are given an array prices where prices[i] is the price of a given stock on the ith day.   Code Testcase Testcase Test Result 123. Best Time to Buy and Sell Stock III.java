class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int firstBuy = Integer.MIN_VALUE;
        int firstSell = 0;
        int secondBuy = Integer.MIN_VALUE;
        int secondSell = 0;

        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price); // Maximum money left after first buy
            firstSell = Math.max(firstSell, firstBuy + price); // Max profit after first sell
            secondBuy = Math.max(secondBuy, firstSell - price); // Maximum money left after second buy
            secondSell = Math.max(secondSell, secondBuy + price); // Max profit after second sell
        }

        return secondSell;
    }
}
