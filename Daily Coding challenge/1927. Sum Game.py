class Solution:
    def sumGame(self, A: str) -> bool:
        sums = [0, 0]
        q = [0, 0]
        n = len(A)

        for i in range(n):
            j = i // (n >> 1)

            if A[i] == '?':
                q[j] += 1
            else:
                sums[j] += int(A[i])

        if (q[0] + q[1]) & 1:
            return True

        return (sums[0] - sums[1]) != ((q[1] - q[0]) >> 1) * 9
