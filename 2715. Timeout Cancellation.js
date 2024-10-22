function cancellable(fn, args, t) {
    let timeoutId; // To hold the ID of the timeout for cancelling
    let executed = false; // Flag to check if the function has been executed

    // Set a timeout to execute the function after `t` milliseconds
    timeoutId = setTimeout(() => {
        const result = fn(...args); // Call the function with the provided arguments
        executed = true; // Set executed flag to true
        console.log([{ time: t, returned: result }]); // Log the result
    }, t);

    // Return the cancel function
    return () => {
        if (!executed) { // If not yet executed
            clearTimeout(timeoutId); // Cancel the timeout
        }
    };
}

// Example Usage:
const cancelTimeMs = 50;

// Example 1
const cancelFn1 = cancellable((x) => x * 5, [2], 20);
setTimeout(cancelFn1, cancelTimeMs); // Will log [{ time: 20, returned: 10 }] after 20ms

// Example 2
const cancelFn2 = cancellable((x) => x ** 2, [2], 100);
setTimeout(cancelFn2, cancelTimeMs); // Will not log anything because cancelFn is called before fn

// Example 3
const cancelFn3 = cancellable((x1, x2) => x1 * x2, [2, 4], 30);
setTimeout(cancelFn3, cancelTimeMs); // Will log [{ time: 30, returned: 8 }] after 30ms
