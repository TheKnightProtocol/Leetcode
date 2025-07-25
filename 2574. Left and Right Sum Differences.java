function leftRightDifference(nums) {
    const n = nums.length;
    const leftSum = Array(n).fill(0);
    const rightSum = Array(n).fill(0);
    const answer = Array(n).fill(0);

    for (let i = 1; i < n; i++) {
        leftSum[i] = leftSum[i - 1] + nums[i - 1];
    }

    for (let i = n - 2; i >= 0; i--) {
        rightSum[i] = rightSum[i + 1] + nums[i + 1];
    }

    for (let i = 0; i < n; i++) {
        answer[i] = Math.abs(leftSum[i] - rightSum[i]);
    }

    return answer;
}

// Example usage:
console.log(leftRightDifference([10, 4, 8, 3])); // Output: [15, 1, 11, 22]
