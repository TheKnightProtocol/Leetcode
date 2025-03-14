import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        if (!wordSet.contains(endWord)) return result;

        // Bidirectional BFS variables
        Map<String, List<String>> adjList = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        // Run bidirectional BFS
        if (!bfs(beginWord, endWord, wordSet, adjList, distance)) return result;

        // Backtracking to get all shortest paths
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, adjList, distance, path, result);
        return result;
    }

    private boolean bfs(String beginWord, String endWord, Set<String> wordSet,
                        Map<String, List<String>> adjList, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> visitedThisLevel = new HashSet<>();

            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                int currDistance = distance.get(currWord);

                for (String neighbor : getNeighbors(currWord, wordSet)) {
                    adjList.putIfAbsent(currWord, new ArrayList<>());
                    adjList.get(currWord).add(neighbor);

                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, currDistance + 1);
                        visitedThisLevel.add(neighbor);
                        queue.offer(neighbor);
                        if (neighbor.equals(endWord)) found = true;
                    }
                }
            }
            wordSet.removeAll(visitedThisLevel); // Remove visited words to avoid cycles
        }
        return found;
    }

    private void dfs(String currWord, String endWord, Map<String, List<String>> adjList,
                     Map<String, Integer> distance, List<String> path, List<List<String>> result) {
        if (currWord.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (!adjList.containsKey(currWord)) return;

        for (String neighbor : adjList.get(currWord)) {
            if (distance.get(neighbor) == distance.get(currWord) + 1) {
                path.add(neighbor);
                dfs(neighbor, endWord, adjList, distance, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] wordArray = word.toCharArray();

        for (int i = 0; i < wordArray.length; i++) {
            char originalChar = wordArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) continue;
                wordArray[i] = c;
                String newWord = new String(wordArray);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            wordArray[i] = originalChar; // Restore original character
        }
        return neighbors;
    }
}

