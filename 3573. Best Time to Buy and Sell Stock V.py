class Solution:
    def maximumProfit(self, prices: List[int], k: int) -> int:
        # best[tx]: max profit with exactly tx transactions, no position
        best = [0] * (k + 1)
        
        # hold[tx]: max profit with tx transactions, holding long (0 <= tx < k)
        hold = [-10**18] * k
        
        # cash[tx]: max profit with tx transactions, holding short (0 <= tx < k)
        cash = [-10**18] * k
        
        for price in prices:
            # Process in reverse to avoid overwriting
            for tx in range(k, 0, -1):
                # Update best[tx] from completing transactions
                best[tx] = max(
                    best[tx],
                    hold[tx - 1] + price,
                    cash[tx - 1] - price
                )
                
                # Update hold[tx-1] from starting new long
                hold[tx - 1] = max(hold[tx - 1], best[tx - 1] - price)
                
                # Update cash[tx-1] from starting new short
                cash[tx - 1] = max(cash[tx - 1], best[tx - 1] + price)
        
        return max(best)
