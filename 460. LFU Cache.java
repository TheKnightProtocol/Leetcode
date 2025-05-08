import java.util.*;

class LFUCache {
    class Node {
        int key, value, freq;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyNode;
    private final Map<Integer, LinkedHashSet<Integer>> freqList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyNode = new HashMap<>();
        this.freqList = new HashMap<>();
    }

    public int get(int key) {
        if (!keyNode.containsKey(key)) return -1;
        Node node = keyNode.get(key);
        updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNode.containsKey(key)) {
            Node node = keyNode.get(key);
            node.value = value;
            updateFreq(node);
        } else {
            if (keyNode.size() >= capacity) {
                LinkedHashSet<Integer> minFreqKeys = freqList.get(minFreq);
                int evictKey = minFreqKeys.iterator().next();
                minFreqKeys.remove(evictKey);
                if (minFreqKeys.isEmpty()) freqList.remove(minFreq);
                keyNode.remove(evictKey);
            }
            Node newNode = new Node(key, value);
            keyNode.put(key, newNode);
            freqList.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            minFreq = 1;
        }
    }

    private void updateFreq(Node node) {
        int freq = node.freq;
        LinkedHashSet<Integer> keys = freqList.get(freq);
        keys.remove(node.key);
        if (keys.isEmpty()) {
            freqList.remove(freq);
            if (freq == minFreq) minFreq++;
        }
        node.freq++;
        freqList.computeIfAbsent(node.freq, k -> new LinkedHashSet<>()).add(node.key);
    }
}
