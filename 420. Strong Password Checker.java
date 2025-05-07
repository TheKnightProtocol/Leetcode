public class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        char[] arr = password.toCharArray();

        for (char c : arr) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }

        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        int replace = 0;
        int oneMod = 0, twoMod = 0;
        for (int i = 2; i < n;) {
            if (arr[i] == arr[i - 1] && arr[i] == arr[i - 2]) {
                int len = 2;
                while (i < n && arr[i] == arr[i - 1]) {
                    len++;
                    i++;
                }
                replace += len / 3;
                if (len % 3 == 0) oneMod++;
                else if (len % 3 == 1) twoMod++;
            } else {
                i++;
            }
        }

        if (n < 6) {
            return Math.max(6 - n, missingTypes);
        } else if (n <= 20) {
            return Math.max(replace, missingTypes);
        } else {
            int delete = n - 20;
            int remainDelete = delete;

            if (remainDelete > 0 && oneMod > 0) {
                int min = Math.min(remainDelete, oneMod);
                replace -= min;
                remainDelete -= min;
            }

            if (remainDelete > 0 && twoMod > 0) {
                int min = Math.min(remainDelete, twoMod * 2);
                replace -= min / 2;
                remainDelete -= min;
            }

            if (remainDelete > 0) {
                replace -= remainDelete / 3;
            }

            return delete + Math.max(replace, missingTypes);
        }
    }
}
