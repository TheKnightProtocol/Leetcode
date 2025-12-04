#include <string>
#include <stack>
using namespace std;

class Solution {
public:
    int countCollisions(string directions) {
        int collisions = 0;
        stack<char> st;
        
        for (char dir : directions) {
            if (dir == 'R') {
                st.push('R');
            } else if (dir == 'S') {
                while (!st.empty() && st.top() == 'R') {
                    collisions += 1;
                    st.pop();
                }
                st.push('S');
            } else { // dir == 'L'
                if (st.empty() || st.top() == 'L') {
                    st.push('L');
                } else {
                    // If top is 'S', it's one collision
                    if (st.top() == 'S') {
                        collisions += 1;
                    } 
                    // If top is 'R', we need to handle all consecutive 'R's
                    else if (st.top() == 'R') {
                        collisions += 2;  // First 'R' collides with 'L'
                        st.pop();
                        
                        // All remaining 'R's will collide with the stationary car
                        while (!st.empty() && st.top() == 'R') {
                            collisions += 1;
                            st.pop();
                        }
                    }
                    // After collision, cars become stationary
                    st.push('S');
                }
            }
        }
        
        return collisions;
    }
};
