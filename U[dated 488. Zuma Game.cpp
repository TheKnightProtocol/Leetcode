#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <queue>

using namespace std;

class Solution {
    string collapse(string s) {
        bool changed = true;
        while (changed) {
            changed = false;
            for (int i = 0, j = 0; i < s.length(); i = j) {
                while (j < s.length() && s[j] == s[i]) j++;
                if (j - i >= 3) {
                    s.erase(i, j - i);
                    changed = true;
                    break;
                }
            }
        }
        return s;
    }

public:
    int findMinStep(string board, string hand) {
        sort(hand.begin(), hand.end());
        queue<pair<string, string>> q;
        q.push({board, hand});
        unordered_map<string, int> visited;
        visited[board + "#" + hand] = 0;

        while (!q.empty()) {
            auto [curr_board, curr_hand] = q.front();
            q.pop();
            int steps = visited[curr_board + "#" + curr_hand];

            for (int j = 0; j < curr_hand.length(); ++j) {
                // Skip duplicate balls in hand
                if (j > 0 && curr_hand[j] == curr_hand[j - 1]) continue;

                for (int i = 0; i <= curr_board.length(); ++i) {
                    // Optimization: Only insert if it matches the current ball 
                    // or it's a strategic "split" insertion
                    bool worth = false;
                    if (i < curr_board.length() && curr_board[i] == curr_hand[j]) worth = true;
                    if (i > 0 && i < curr_board.length() && curr_board[i - 1] == curr_board[i] && curr_board[i] != curr_hand[j]) worth = true;

                    if (!worth) continue;

                    string next_board = collapse(curr_board.substr(0, i) + curr_hand[j] + curr_board.substr(i));
                    if (next_board.empty()) return steps + 1;

                    string next_hand = curr_hand.substr(0, j) + curr_hand.substr(j + 1);
                    string state = next_board + "#" + next_hand;

                    if (!visited.count(state)) {
                        visited[state] = steps + 1;
                        q.push({next_board, next_hand});
                    }
                }
            }
        }
        return -1;
    }
};
