class Solution:
    def maxProfit(self, n, present, future, hierarchy, budget):
        from collections import defaultdict
        from functools import lru_cache

        tree = defaultdict(list)
        for u, v in hierarchy:
            tree[u].append(v)

        NEG = -10**15

        def merge(a, b):
            res = [NEG] * (budget + 1)
            for i in range(budget + 1):
                if a[i] == NEG:
                    continue
                for j in range(budget - i + 1):
                    if b[j] == NEG:
                        continue
                    res[i + j] = max(res[i + j], a[i] + b[j])
            return res

        @lru_cache(None)
        def dfs(u, parent_bought):
            # Option 1: do NOT buy u
            dp_no = [0] + [NEG] * budget
            for v in tree[u]:
                dp_no = merge(dp_no, dfs(v, 0))

            best = dp_no[:]

            # Option 2: BUY u (even if profit is negative!)
            cost = present[u - 1] if not parent_bought else present[u - 1] // 2
            profit = future[u - 1] - cost

            if cost <= budget:
                dp_yes = [NEG] * (budget + 1)
                dp_yes[cost] = profit
                for v in tree[u]:
                    dp_yes = merge(dp_yes, dfs(v, 1))

                for b in range(budget + 1):
                    best[b] = max(best[b], dp_yes[b])

            return tuple(best)

        return max(dfs(1, 0))
