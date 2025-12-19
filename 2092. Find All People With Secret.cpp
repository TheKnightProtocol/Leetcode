#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

class DSU {
private:
    vector<int> parent, rank;
public:
    DSU(int n) {
        parent.resize(n);
        rank.resize(n, 0);
        iota(parent.begin(), parent.end(), 0);
    }
    
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    void unite(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }
    
    bool connected(int x, int y) {
        return find(x) == find(y);
    }
    
    void reset(int x) {
        parent[x] = x;
        rank[x] = 0;
    }
};

class Solution {
public:
    vector<int> findAllPeople(int n, vector<vector<int>>& meetings, int firstPerson) {
        sort(meetings.begin(), meetings.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });
        
        DSU dsu(n);
        dsu.unite(0, firstPerson);
        
        int m = meetings.size();
        vector<int> people;
        for (int i = 0; i < m; ) {
            int j = i;
            while (j < m && meetings[j][2] == meetings[i][2]) {
                j++;
            }
            
            people.clear();
            for (int k = i; k < j; k++) {
                dsu.unite(meetings[k][0], meetings[k][1]);
                people.push_back(meetings[k][0]);
                people.push_back(meetings[k][1]);
            }
            
            for (int person : people) {
                if (!dsu.connected(person, 0)) {
                    dsu.reset(person);
                }
            }
            
            i = j;
        }
        
        vector<int> result;
        for (int i = 0; i < n; i++) {
            if (dsu.connected(i, 0)) {
                result.push_back(i);
            }
        }
        return result;
    }
};
