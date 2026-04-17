#include <string>
#include <vector>
#include <stack>

using namespace std;

class Solution {
public:
    string removeDuplicateLetters(string s) {
        vector<int> freq(26, 0);
        vector<bool> inStack(26, false);
        stack<char> st;
        
        for (char c : s) {
            freq[c - 'a']++;
        }
        
        for (char c : s) {
            int idx = c - 'a';
            freq[idx]--;
            
            if (inStack[idx]) continue;
            
            while (!st.empty() && c < st.top() && freq[st.top() - 'a'] > 0) {
                inStack[st.top() - 'a'] = false;
                st.pop();
            }
            
            st.push(c);
            inStack[idx] = true;
        }
        
        string result = "";
        while (!st.empty()) {
            result = st.top() + result;
            st.pop();
        }
        
        return result;
    }
};
