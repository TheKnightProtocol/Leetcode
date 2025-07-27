import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int n) {
        if (current.length() == n) {
            result.add(current.toString());
            return;
        }
        
        for (char c : new char[]{'0', '1'}) {
            if (current.length() > 0 && current.charAt(current.length() - 1) == '0' && c == '0') {
                continue;
            }
            current.append(c);
            backtrack(result, current, n);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
