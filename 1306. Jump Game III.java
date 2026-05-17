class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] vis = new boolean[arr.length];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        vis[start] = true;
        while (!q.isEmpty()) {
            int i = q.poll();
            if (arr[i] == 0) return true;
            int left = i - arr[i];
            if (left >= 0 && !vis[left]) {
                vis[left] = true;
                q.offer(left);
            }
            int right = i + arr[i];
            if (right < arr.length && !vis[right]) {
                vis[right] = true;
                q.offer(right);
            }
        }
        return false;
    }
}
