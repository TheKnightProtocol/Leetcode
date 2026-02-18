class Solution {
public:
    int minSwapsCouples(vector<int>& row) {
        int n = row.size();
        vector<int> pos(n);
        
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }
        
        int swaps = 0;
        
        for (int i = 0; i < n; i += 2) {
            int person1 = row[i];
            int person2 = (person1 % 2 == 0) ? person1 + 1 : person1 - 1;
            
            if (row[i + 1] != person2) {
                int partnerPos = pos[person2];
                swap(row[i + 1], row[partnerPos]);
                pos[row[partnerPos]] = partnerPos;
                pos[row[i + 1]] = i + 1;
                swaps++;
            }
        }
        
        return swaps;
    }
};
