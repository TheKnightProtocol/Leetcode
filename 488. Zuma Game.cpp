#include <string>
#include <unordered_map>
#include <climits>
using namespace std;

class Solution {
public:
    unordered_map<string, int> memo;

    int findMinStep(string board, string hand) {
        int res = dfs(board, hand);
        return res == INT_MAX ? -1 : res;
    }

    int dfs(string board, string hand) {
        if (board.empty()) return 0;
        string key = board + "#" + hand;
        if (memo.count(key)) return memo[key];
        
        int res = INT_MAX;

        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < hand.size(); ++j) {
                if (j > 0 && hand[j] == hand[j - 1]) continue;

                string new_board = board.substr(0, i) + hand[j] + board.substr(i);
                string reduced = shrink(new_board);
                string new_hand = hand.substr(0, j) + hand.substr(j + 1);
                int temp = dfs(reduced, new_hand);
                if (temp != INT_MAX)
                    res = min(res, 1 + temp);
            }
        }

        return memo[key] = res;
    }

    string shrink(string board) {
        int i = 0;
        while (i < board.size()) {
            int j = i;
            while (j < board.size() && board[j] == board[i]) ++j;
            if (j - i >= 3)
                return shrink(board.substr(0, i) + board.substr(j));
            else
                ++i;
        }
        return board;
    }
};
