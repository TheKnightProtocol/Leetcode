import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min Heap

        // Maintain a heap of size k
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element
            }
        }
        
        return minHeap.peek(); // The root of the heap is the Kth largest element
    }
}
