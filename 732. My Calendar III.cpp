class MyCalendarThree {
private:
    map<int, int> timeline;
    int maxK;
    
public:
    MyCalendarThree() {
        maxK = 0;
    }
    
    int book(int startTime, int endTime) {
        timeline[startTime]++;
        timeline[endTime]--;
        
        int currentK = 0;
        for (auto& [time, count] : timeline) {
            currentK += count;
            maxK = max(maxK, currentK);
        }
        
        return maxK;
    }
};
