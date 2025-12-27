#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class Solution {
public:
    int mostBooked(int n, vector<vector<int>>& meetings) {
        sort(meetings.begin(), meetings.end());
        
        priority_queue<int, vector<int>, greater<int>> freeRooms;
        for (int i = 0; i < n; i++) freeRooms.push(i);
        
        priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> busy;
        
        vector<int> count(n, 0);
        
        for (auto& meeting : meetings) {
            long long start = meeting[0];
            long long duration = meeting[1] - meeting[0];
            
            while (!busy.empty() && busy.top().first <= start) {
                freeRooms.push(busy.top().second);
                busy.pop();
            }
            
            if (!freeRooms.empty()) {
                int room = freeRooms.top();
                freeRooms.pop();
                count[room]++;
                busy.push({start + duration, room});
            } else {
                auto [endTime, room] = busy.top();
                busy.pop();
                count[room]++;
                busy.push({endTime + duration, room});
            }
        }
        
        int maxMeetings = 0, resultRoom = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > maxMeetings) {
                maxMeetings = count[i];
                resultRoom = i;
            }
        }
        
        return resultRoom;
    }
};
