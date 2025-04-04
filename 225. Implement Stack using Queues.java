import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    private Queue<Integer> q1; // Primary queue
    private Queue<Integer> q2; // Temporary queue

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q2.offer(x); // Push the new element into q2
        while (!q1.isEmpty()) {
            q2.offer(q1.poll()); // Move all elements from q1 to q2
        }
        // Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.poll(); // Remove and return the top element
    }

    public int top() {
        return q1.peek(); // Return the top element without removing it
    }

    public boolean empty() {
        return q1.isEmpty(); // Check if the stack is empty
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // Output: 2
        System.out.println(myStack.pop()); // Output: 2
        System.out.println(myStack.empty()); // Output: false
    }
}
