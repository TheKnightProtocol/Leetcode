import java.util.*;

class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];
        boolean[] online = new boolean[numberOfUsers];
        Arrays.fill(online, true);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Sort events by timestamp
        Collections.sort(events, Comparator.comparingInt(e -> Integer.parseInt(e.get(1))));

        int i = 0;
        while (i < events.size()) {
            int currentTime = Integer.parseInt(events.get(i).get(1));
            
            // Process all users coming back online before this timestamp
            while (!pq.isEmpty() && pq.peek()[1] <= currentTime) {
                int userId = pq.poll()[0];
                online[userId] = true;
            }
            
            // First process all OFFLINE events at this timestamp
            int j = i;
            while (j < events.size() && Integer.parseInt(events.get(j).get(1)) == currentTime) {
                if (events.get(j).get(0).equals("OFFLINE")) {
                    int userId = Integer.parseInt(events.get(j).get(2));
                    online[userId] = false;
                    pq.offer(new int[]{userId, currentTime + 60});
                }
                j++;
            }
            
            // Then process all MESSAGE events at this timestamp
            for (int k = i; k < j; k++) {
                if (events.get(k).get(0).equals("MESSAGE")) {
                    String mentionStr = events.get(k).get(2);
                    if (mentionStr.equals("ALL")) {
                        for (int user = 0; user < numberOfUsers; user++) {
                            mentions[user]++;
                        }
                    } else if (mentionStr.equals("HERE")) {
                        for (int user = 0; user < numberOfUsers; user++) {
                            if (online[user]) mentions[user]++;
                        }
                    } else {
                        String[] tokens = mentionStr.split(" ");
                        for (String token : tokens) {
                            int userId = Integer.parseInt(token.substring(2));
                            mentions[userId]++;
                        }
                    }
                }
            }
            
            i = j;
        }
        return mentions;
    }
}
