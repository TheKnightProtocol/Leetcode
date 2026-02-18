class Solution {
public:
    int minSwap(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int swap = 1;
        int noSwap = 0;
        
        for (int i = 1; i < n; i++) {
            int newSwap = INT_MAX;
            int newNoSwap = INT_MAX;
            
            if (nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]) {
                newNoSwap = min(newNoSwap, noSwap);
                newSwap = min(newSwap, swap + 1);
            }
            
            if (nums1[i] > nums2[i-1] && nums2[i] > nums1[i-1]) {
                newNoSwap = min(newNoSwap, swap);
                newSwap = min(newSwap, noSwap + 1);
            }
            
            swap = newSwap;
            noSwap = newNoSwap;
        }
        
        return min(swap, noSwap);
    }
};
