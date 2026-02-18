class RangeModule {
private:
    map<int, int> intervals;
    
public:
    RangeModule() {}
    
    void addRange(int left, int right) {
        auto it = intervals.upper_bound(left);
        if (it != intervals.begin()) {
            auto prevIt = prev(it);
            if (prevIt->second >= left) {
                left = prevIt->first;
                right = max(right, prevIt->second);
                intervals.erase(prevIt);
            }
        }
        
        while (it != intervals.end() && it->first <= right) {
            right = max(right, it->second);
            it = intervals.erase(it);
        }
        
        intervals[left] = right;
    }
    
    bool queryRange(int left, int right) {
        auto it = intervals.upper_bound(left);
        if (it == intervals.begin()) return false;
        it = prev(it);
        return it->second >= right;
    }
    
    void removeRange(int left, int right) {
        auto it = intervals.upper_bound(left);
        if (it != intervals.begin()) {
            auto prevIt = prev(it);
            if (prevIt->second > left) {
                int newLeft = prevIt->first;
                int newRight = prevIt->second;
                intervals.erase(prevIt);
                if (newLeft < left) {
                    intervals[newLeft] = left;
                }
                if (newRight > right) {
                    intervals[right] = newRight;
                }
            }
        }
        
        while (it != intervals.end() && it->first < right) {
            if (it->second <= right) {
                it = intervals.erase(it);
            } else {
                intervals[right] = it->second;
                intervals.erase(it);
                break;
            }
        }
    }
};
