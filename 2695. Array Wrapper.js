class ArrayWrapper {
  constructor(nums) {
    this.nums = nums;
  }

  // Overloading the + operator via valueOf
  valueOf() {
    return this.nums.reduce((acc, num) => acc + num, 0);
  }

  // String representation for String() via toString
  toString() {
    return `[${this.nums.join(',')}]`;
  }
}

// Example test cases:

// Example 1:
const obj1 = new ArrayWrapper([1, 2]);
const obj2 = new ArrayWrapper([3, 4]);
console.log(obj1 + obj2);  // Output: 10

// Example 2:
const obj = new ArrayWrapper([23, 98, 42, 70]);
console.log(String(obj));  // Output: "[23,98,42,70]"

// Example 3:
const obj3 = new ArrayWrapper([]);
const obj4 = new ArrayWrapper([]);
console.log(obj3 + obj4);  // Output: 0
