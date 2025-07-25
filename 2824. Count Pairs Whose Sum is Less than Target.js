function countPairs(nums, target) {
    let count = 0;

    for (let i = 0; i < nums.length; i++) {
        for (let j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] < target) {
                count++;
            }
        }
    }

    return count;
}

// Example 1
console.log(countPairs([-1, 1, 2, 3, 1], 2)); // Output: 3

// Example 2
console.log(countPairs([-6, 2, 5, -2, -7, -1, 3], -2)); // Output: 10
