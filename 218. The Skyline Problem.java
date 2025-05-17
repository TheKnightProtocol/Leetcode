class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new int[]{b[0], -b[2]});
            points.add(new int[]{b[1], b[2]});
        }

        points.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.add(0);

        List<List<Integer>> result = new ArrayList<>();
        int prevMax = 0;

        for (int[] p : points) {
            if (p[1] < 0) {
                pq.add(-p[1]);
            } else {
                pq.remove(p[1]);
            }

            int currMax = pq.peek();
            if (currMax != prevMax) {
                result.add(Arrays.asList(p[0], currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}
