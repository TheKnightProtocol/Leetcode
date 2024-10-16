class Solution:
    def scoreOfString(self, s):
        score = 0
        for i in range(len(s) - 1):
            score += abs(ord(s[i]) - ord(s[i + 1]))
        return score

# Example usage
if __name__ == "__main__":
    solution = Solution()
    s = "hello"
    result = solution.scoreOfString(s)
    print(f"Score of the string '{s}': {result}")  # Output: Score of the string 'hello': 13
