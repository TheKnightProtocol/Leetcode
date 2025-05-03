import java.util.*;

public class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> result = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build the graph
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        // Step 2: Start DFS from JFK
        dfs("JFK");

        // Step 3: Return the itinerary
        return result;
    }

    private void dfs(String airport) {
        PriorityQueue<String> dests = graph.get(airport);
        while (dests != null && !dests.isEmpty()) {
            String next = dests.poll();
            dfs(next);
        }
        result.addFirst(airport); // Add to the front as we backtrack
    }
}
