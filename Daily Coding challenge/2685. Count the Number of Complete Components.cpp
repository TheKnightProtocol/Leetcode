class Solution {
public:
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        vector<int> root(n);
        iota(root.begin(), root.end(), 0);

        auto find = [&](this auto& self, int i) -> int {
            return root[i] == i ? i : root[i] = self(root[i]);
        };

        for (auto& e : edges)
            root[find(e[0])] = find(e[1]);

        vector<int> V(n, 0);
        vector<int> E(n, 0);

        for (int i = 0; i < n; i++)
            V[find(i)]++;

        for (auto& e : edges)
            E[find(e[0])]++;

        int res = 0;
        for (int i = 0; i < n; i++)
            res += (V[i] && E[i] == V[i] * (V[i] - 1) >> 1);

        return res;
    }
};
