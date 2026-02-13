class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
        if (start == destination) return 0;
        
        int n = distance.size();
        int clockwise = 0;
        int counterclockwise = 0;
        
        if (start < destination) {
            for (int i = start; i < destination; i++) {
                clockwise += distance[i];
            }
            for (int i = destination; i < n; i++) {
                counterclockwise += distance[i];
            }
            for (int i = 0; i < start; i++) {
                counterclockwise += distance[i];
            }
        } else {
            for (int i = start; i < n; i++) {
                clockwise += distance[i];
            }
            for (int i = 0; i < destination; i++) {
                clockwise += distance[i];
            }
            for (int i = destination; i < start; i++) {
                counterclockwise += distance[i];
            }
        }
        
        return min(clockwise, counterclockwise);
    }
};
