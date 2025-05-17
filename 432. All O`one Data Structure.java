import java.util.*;

class AllOne {

    class Node {
        int count;
        Set<String> keys;
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new LinkedHashSet<>(); // preserves insertion order
        }
    }

    private Map<String, Node> keyCountMap;
    private Node head, tail;

    public AllOne() {
        keyCountMap = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    // Insert a new node with count next to prevNode
    private Node insertAfter(Node prevNode, int count) {
        Node newNode = new Node(count);
        newNode.prev = prevNode;
        newNode.next = prevNode.next;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
        return newNode;
    }

    // Remove a node if its key set is empty
    private void removeNode(Node node) {
        if (node != head && node != tail && node.keys.isEmpty()) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void inc(String key) {
        Node curr = keyCountMap.getOrDefault(key, head);
        Node nextNode;

        if (curr.next == tail || curr.next.count != curr.count + 1) {
            nextNode = insertAfter(curr, curr.count + 1);
        } else {
            nextNode = curr.next;
        }

        nextNode.keys.add(key);
        if (curr != head) {
            curr.keys.remove(key);
            removeNode(curr);
        }

        keyCountMap.put(key, nextNode);
    }

    public void dec(String key) {
        Node curr = keyCountMap.get(key);
        if (curr == null) return;

        if (curr.count == 1) {
            keyCountMap.remove(key);
        } else {
            Node prevNode;
            if (curr.prev == head || curr.prev.count != curr.count - 1) {
                prevNode = insertAfter(curr.prev, curr.count - 1);
            } else {
                prevNode = curr.prev;
            }

            prevNode.keys.add(key);
            keyCountMap.put(key, prevNode);
        }

        curr.keys.remove(key);
        removeNode(curr);
    }

    public String getMaxKey() {
        return tail.prev != head && !tail.prev.keys.isEmpty()
                ? tail.prev.keys.iterator().next()
                : "";
    }

    public String getMinKey() {
        return head.next != tail && !head.next.keys.isEmpty()
                ? head.next.keys.iterator().next()
                : "";
    }
}
