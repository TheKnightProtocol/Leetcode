import java.util.*;

public class Solution {
    private Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // If the node is already cloned, return it.
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Clone the current node
        Node clone = new Node(node.val);
        map.put(node, clone);

        // Clone all neighbors recursively
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}
