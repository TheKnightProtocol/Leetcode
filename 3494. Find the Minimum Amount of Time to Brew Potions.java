class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        
        long[][] end = new long[n][m];
        
        // First potion
        end[0][0] = (long) skill[0] * mana[0];
        for (int i = 1; i < n; i++) {
            end[i][0] = end[i-1][0] + (long) skill[i] * mana[0];
        }
        
        for (int j = 1; j < m; j++) {
            // Binary search for the earliest start time that works
            long low = end[0][j-1];
            long high = low + 1000000L; // Large enough upper bound
            
            while (low < high) {
                long mid = low + (high - low) / 2;
                if (isValidStart(mid, j, skill, mana, end, n)) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            
            long startTime = low;
            end[0][j] = startTime + (long) skill[0] * mana[j];
            for (int i = 1; i < n; i++) {
                // The start time for wizard i is max of:
                // 1. When wizard i finishes previous potion
                // 2. When previous wizard finishes this potion
                long wizardIStart = Math.max(end[i][j-1], end[i-1][j]);
                end[i][j] = wizardIStart + (long) skill[i] * mana[j];
            }
        }
        
        return end[n-1][m-1];
    }
    
    private boolean isValidStart(long startTime, int j, int[] skill, int[] mana, long[][] end, int n) {
        // Check if with this start time, potion j can flow continuously through all wizards
        long currentTime = startTime;
        
        for (int i = 0; i < n; i++) {
            // When will potion j reach wizard i?
            long arrivalAtWizardI = currentTime;
            
            // Wizard i must be free by this time
            if (arrivalAtWizardI < end[i][j-1]) {
                return false;
            }
            
            // Move to next wizard
            currentTime += (long) skill[i] * mana[j];
        }
        
        return true;
    }
}
