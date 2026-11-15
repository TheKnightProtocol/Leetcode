from typing import List

class Solution:
    def numOfSubarrays(self, arr: List[int]) -> int:
        MOD = 10**9 + 7

        even = 1  # empty prefix sum 0 is even
        odd = 0
        prefix_parity = 0
        count = 0

        for num in arr:
            prefix_parity ^= (num & 1)  # update parity

            if prefix_parity == 1:
                # Current prefix is odd, need previous even prefixes
                count += even
            else:
                # Current prefix is even, need previous odd prefixes
                count += odd

            # Record current prefix parity
            if prefix_parity == 1:
                odd += 1
            else:
                even += 1

        return count % MOD
