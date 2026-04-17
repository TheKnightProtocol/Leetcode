class Solution {
public:
    bool isHappy(int n) {
        int slow = n;
        int fast = sumOfSquares(n);
        
        while (fast != 1 && slow != fast) {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        }
        
        return fast == 1;
    }
    
private:
    int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
};
