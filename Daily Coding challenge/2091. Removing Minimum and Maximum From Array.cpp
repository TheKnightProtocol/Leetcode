class Solution {
public:
    int minimumDeletions(vector<int>& A) {
    int n = A.size();
    auto [a, b] = ranges::minmax_element(A);
    auto [x, y] = minmax(a, b);

    int L = x - A.begin();
    int R = y - A.begin();

    return min({L + 1 + n - R, R + 1, n - L});

        
    }
};
