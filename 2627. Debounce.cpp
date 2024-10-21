function debounce(fn, t) {
    let timeoutId;

    return function(...args) {
        // Clear the previous timeout if it exists
        if (timeoutId) {
            clearTimeout(timeoutId);
        }

        // Set a new timeout to execute the function after the specified time
        timeoutId = setTimeout(() => {
            fn(...args);
        }, t);
    };
}

// Example usage:

// Example function to be debounced
function logMessage(message) {
    console.log(message);
}

// Create a debounced version of `logMessage` with a 50ms delay
const debouncedLog = debounce(logMessage, 50);

// Test the debounced function
debouncedLog("First call");   // This call will be cancelled
debouncedLog("Second call");  // This call will be cancelled
debouncedLog("Third call");   // This call will be executed after 50ms
