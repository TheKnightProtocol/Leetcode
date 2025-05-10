function addTwoPromises(promise1, promise2) {
  return Promise.all([promise1, promise2])
    .then(([val1, val2]) => val1 + val2);
}

// Example usage:
const promise1 = new Promise(resolve => setTimeout(() => resolve(2), 20));
const promise2 = new Promise(resolve => setTimeout(() => resolve(5), 60));

addTwoPromises(promise1, promise2).then(result => console.log(result)); // Output: 7
