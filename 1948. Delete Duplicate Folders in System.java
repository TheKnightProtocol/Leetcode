import java.util.*;

class Solution {
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode();
        for (List<String> path : paths) {
            TrieNode curr = root;
            for (String folder : path) {
                if (!curr.children.containsKey(folder)) {
                    curr.children.put(folder, new TrieNode());
                }
                curr = curr.children.get(folder);
            }
        }

        Map<String, TrieNode> seen = new HashMap<>();
        buildHash(root, seen);

        List<List<String>> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);

        return result; 
    }

    private String buildHash(TrieNode node, Map<String, TrieNode> seen) {
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<>(node.children.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            TrieNode child = node.children.get(key);
            sb.append(key).append(buildHash(child, seen)).append("|");
        }
        String hash = sb.toString();
        if (!hash.isEmpty()) {
            if (seen.containsKey(hash)) {
                seen.get(hash).marked = true;
                node.marked = true;
            } else {
                seen.put(hash, node);
            }
        }
        return hash;
    }

    private void dfs(TrieNode node, List<String> path, List<List<String>> result) {
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            String folder = entry.getKey();
            TrieNode child = entry.getValue();
            if (!child.marked) {
                List<String> newPath = new ArrayList<>(path);
                newPath.add(folder);
                result.add(newPath);
                dfs(child, newPath, result);
            }
        }
    }

    static class TrieNode {
        Map<String, TrieNode> children;
        boolean marked;

        TrieNode() {
            children = new HashMap<>();
            marked = false;
        }
    }
}
