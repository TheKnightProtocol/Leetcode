class PeekingIterator : public Iterator {
private:
    bool hasPeeked;
    int peekValue;
public:
    PeekingIterator(const vector<int>& nums) : Iterator(nums) {
        hasPeeked = false;
    }
    
    int peek() {
        if (!hasPeeked) {
            peekValue = Iterator::next();
            hasPeeked = true;
        }
        return peekValue;
    }
    
    int next() {
        if (hasPeeked) {
            hasPeeked = false;
            return peekValue;
        }
        return Iterator::next();
    }
    
    bool hasNext() const {
        return hasPeeked || Iterator::hasNext();
    }
};
