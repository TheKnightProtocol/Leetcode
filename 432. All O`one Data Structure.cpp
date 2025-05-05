#include <unordered_map>
#include <unordered_set>
#include <list>
#include <string>

using namespace std;

class AllOne {
    struct Bucket {
        int count;
        unordered_set<string> keys;
    };

    list<Bucket> bucketList;
    unordered_map<string, int> keyCount;
    unordered_map<int, list<Bucket>::iterator> countBucket;

public:
    AllOne() {}

    void inc(string key) {
        int count = keyCount[key]++;
        auto curIt = count ? countBucket[count] : bucketList.end();
        auto nextIt = curIt;

        if (countBucket.find(count + 1) == countBucket.end()) {
            if (curIt == bucketList.end()) {
                bucketList.push_front({count + 1, {key}});
                countBucket[count + 1] = bucketList.begin();
            } else {
                nextIt = bucketList.insert(nextIt, {count + 1, {key}});
                countBucket[count + 1] = nextIt;
            }
        } else {
            countBucket[count + 1]->keys.insert(key);
        }

        if (count) {
            curIt->keys.erase(key);
            if (curIt->keys.empty()) {
                countBucket.erase(count);
                bucketList.erase(curIt);
            }
        }
    }

    void dec(string key) {
        int count = keyCount[key]--;
        auto curIt = countBucket[count];

        if (count == 1) {
            keyCount.erase(key);
        } else {
            if (countBucket.find(count - 1) == countBucket.end()) {
                auto newIt = bucketList.insert(curIt, {count - 1, {key}});
                countBucket[count - 1] = newIt;
            } else {
                countBucket[count - 1]->keys.insert(key);
            }
        }

        curIt->keys.erase(key);
        if (curIt->keys.empty()) {
            countBucket.erase(count);
            bucketList.erase(curIt);
        }
    }

    string getMaxKey() {
        return bucketList.empty() ? "" : *bucketList.back().keys.begin();
    }

    string getMinKey() {
        return bucketList.empty() ? "" : *bucketList.front().keys.begin();
    }
};
