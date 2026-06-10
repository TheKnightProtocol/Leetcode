#include <stack>
#include <algorithm>
using namespace std;

class MinStack {
    stack<int> data, minStack;
public:
    MinStack() {}

    void push(int val) {
        data.push(val);
        if (minStack.empty() || val <= minStack.top())
            minStack.push(val);
    }

    void pop() {
        if (data.top() == minStack.top())
            minStack.pop();
        data.pop();
    }

    int top() {
        return data.top();
    }

    int getMin() {
        return minStack.top();
    }
};
