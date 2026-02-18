class Solution {
public:
    int numBusesToDestination(vector<vector<int>>& routes, int source, int target) {
        if (source == target) return 0;
        
        unordered_map<int, vector<int>> stopToRoutes;
        for (int i = 0; i < routes.size(); i++) {
            for (int stop : routes[i]) {
                stopToRoutes[stop].push_back(i);
            }
        }
        
        queue<int> q;
        unordered_set<int> visitedStops;
        vector<bool> visitedRoutes(routes.size(), false);
        
        q.push(source);
        visitedStops.insert(source);
        
        int buses = 0;
        
        while (!q.empty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int currentStop = q.front();
                q.pop();
                
                if (currentStop == target) {
                    return buses;
                }
                
                for (int routeIndex : stopToRoutes[currentStop]) {
                    if (visitedRoutes[routeIndex]) continue;
                    
                    visitedRoutes[routeIndex] = true;
                    
                    for (int stop : routes[routeIndex]) {
                        if (!visitedStops.count(stop)) {
                            visitedStops.insert(stop);
                            q.push(stop);
                        }
                    }
                }
            }
            
            buses++;
        }
        
        return -1;
    }
};
