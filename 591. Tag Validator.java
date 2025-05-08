import java.util.Stack;

public class Solution {
    public boolean isValid(String code) {
        if (code == null || code.length() == 0 || code.charAt(0) != '<') return false;
        int i = 0, n = code.length();
        Stack<String> stack = new Stack<>();

        while (i < n) {
            if (i > 0 && stack.isEmpty()) return false;
            if (code.startsWith("<![CDATA[", i)) {
                int j = code.indexOf("]]>", i);
                if (j < 0) return false;
                i = j + 3;
            } else if (code.startsWith("</", i)) {
                int j = code.indexOf(">", i);
                if (j < 0) return false;
                String tag = code.substring(i + 2, j);
                if (tag.length() < 1 || tag.length() > 9) return false;
                for (char c : tag.toCharArray()) {
                    if (c < 'A' || c > 'Z') return false;
                }
                if (stack.isEmpty() || !stack.pop().equals(tag)) return false;
                i = j + 1;
            } else if (code.startsWith("<", i)) {
                int j = code.indexOf(">", i);
                if (j < 0 || j == i + 1 || j - i - 1 > 9) return false;
                String tag = code.substring(i + 1, j);
                for (char c : tag.toCharArray()) {
                    if (c < 'A' || c > 'Z') return false;
                }
                stack.push(tag);
                i = j + 1;
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }
}
