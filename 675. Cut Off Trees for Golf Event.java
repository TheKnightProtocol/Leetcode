
import java.util.*;

class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (forest.get(i).get(j) > 1)
                    trees.add(new int[]{forest.get(i).get(j), i, j});

        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));

        int totalSteps = 0, sx = 0, sy = 0;

        for (int[] tree : trees) {
            int steps = bfs(forest, sx, sy, tree[1], tree[2]);
            if (steps == -1) return -1;
            totalSteps += steps;
            sx = tree[1];
            sy = tree[2];
        }

        return totalSteps;
    }

    private int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return 0;
        int m = forest.size(), n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : dirs) {
                int nx = cur[0] + d[0], ny = cur[1] + d[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                    if (nx == tx && ny == ty) return cur[2] + 1;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, cur[2] + 1});
                }
            }
        }

        return -1;
    }
}
