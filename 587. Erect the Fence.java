public class Solution {
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n <= 3) return trees;

        Arrays.sort(trees, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        Set<List<Integer>> hull = new HashSet<>();

        Stack<int[]> lower = new Stack<>();
        for (int[] p : trees) {
            while (lower.size() >= 2 && cross(lower.get(lower.size() - 2), lower.peek(), p) < 0) {
                lower.pop();
            }
            lower.push(p);
        }

        Stack<int[]> upper = new Stack<>();
        for (int i = trees.length - 1; i >= 0; i--) {
            int[] p = trees[i];
            while (upper.size() >= 2 && cross(upper.get(upper.size() - 2), upper.peek(), p) < 0) {
                upper.pop();
            }
            upper.push(p);
        }

        for (int[] p : lower) hull.add(Arrays.asList(p[0], p[1]));
        for (int[] p : upper) hull.add(Arrays.asList(p[0], p[1]));

        // Convert to int[][]
        int[][] res = new int[hull.size()][2];
        int i = 0;
        for (List<Integer> point : hull) {
            res[i][0] = point.get(0);
            res[i][1] = point.get(1);
            i++;
        }

        return res;
    }

    private int cross(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) -
               (b[1] - a[1]) * (c[0] - a[0]);
    }
}
