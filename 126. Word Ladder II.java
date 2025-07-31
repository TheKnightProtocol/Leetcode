import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!wordSet.contains(endWord)) return res;

        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        dist.put(beginWord, 0);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int curDist = dist.get(word);

            for (String neighbor : getNeighbors(word, wordSet)) {
                if (!dist.containsKey(neighbor)) {
                    dist.put(neighbor, curDist + 1);
                    queue.offer(neighbor);
                    adj.putIfAbsent(word, new ArrayList<>());
                    adj.get(word).add(neighbor);
                } else if (dist.get(neighbor) == curDist + 1) {
                    adj.putIfAbsent(word, new ArrayList<>());
                    adj.get(word).add(neighbor);
                }
            }
        }

        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, adj, path, res);
        return res;
    }

    private void dfs(String word, String endWord, Map<String, List<String>> adj,
                     List<String> path, List<List<String>> res) {
        if (word.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (!adj.containsKey(word)) return;
        for (String next : adj.get(word)) {
            path.add(next);
            dfs(next, endWord, adj, path, res);
            path.remove(path.size() - 1);
        }
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] arr = word.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char old = arr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) continue;
                arr[i] = c;
                String newWord = new String(arr);
                if (wordSet.contains(newWord)) neighbors.add(newWord);
            }
            arr[i] = old;
        }
        return neighbors;
    }
}
