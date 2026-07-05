class Solution {
public:
    vector<int> pathsWithMaxScore(vector<string>& board) {
        const int MOD = 1000000007;
        int n = board.size();
        vector<int> score(n + 1, -1);
        vector<int> ways(n + 1, 0);

        for (int r = n - 1; r >= 0; r--) {
            vector<int> newScore(n + 1, -1);
            vector<int> newWays(n + 1, 0);
            for (int c = n - 1; c >= 0; c--) {
                if (board[r][c] == 'X') 
                    continue;
                if (board[r][c] == 'S') {
                    newScore[c] = 0;
                    newWays[c] = 1;
                    continue;
                }

                int best = max({score[c], newScore[c + 1], score[c + 1]});

                if (best == -1) 
                    continue;

                long long cnt = 0;

                if (score[c] == best) 
                    cnt += ways[c];

                if (newScore[c + 1] == best) 
                    cnt += newWays[c + 1];

                if (score[c + 1] == best) 
                    cnt += ways[c + 1];
                int val = board[r][c] == 'E' ? 0 : board[r][c] - '0';
                newScore[c] = best + val;
                newWays[c] = cnt % MOD;
            }
            score = newScore;
            ways = newWays;
        }

        if (score[0] == -1) 
            return {0, 0};
        return {score[0], ways[0]};
    }
};
