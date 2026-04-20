class Solution {
public:
    string getHint(string secret, string guess) {
        int bulls = 0;
        vector<int> countSecret(10, 0), countGuess(10, 0);
        
        for (int i = 0; i < secret.size(); ++i) {
            if (secret[i] == guess[i]) {
                bulls++;
            } else {
                countSecret[secret[i] - '0']++;
                countGuess[guess[i] - '0']++;
            }
        }
        
        int cows = 0;
        for (int d = 0; d < 10; ++d) {
            cows += min(countSecret[d], countGuess[d]);
        }
        
        return to_string(bulls) + "A" + to_string(cows) + "B";
    }
};
