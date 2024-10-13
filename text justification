import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justifiedText = new ArrayList<>();
        int n = words.length;
        int i = 0;

        while (i < n) {
            int lineLength = 0;
            int start = i;

            // Determine how many words can fit on the current line
            while (i < n && lineLength + words[i].length() + (i - start) <= maxWidth) {
                lineLength += words[i].length();
                i++;
            }

            // Prepare the current line
            StringBuilder currentLine = new StringBuilder();
            int totalSpaces = maxWidth - lineLength; // total spaces to distribute
            int numberOfWords = i - start; // number of words in the current line

            // If this is the last line or the line contains only one word
            if (i == n || numberOfWords == 1) {
                for (int j = start; j < i; j++) {
                    currentLine.append(words[j]);
                    if (j < i - 1) {
                        currentLine.append(" "); // add single space between words
                    }
                }
                // Add padding spaces to the end of the last line
                while (currentLine.length() < maxWidth) {
                    currentLine.append(" ");
                }
            } else {
                // Calculate spaces between words
                int spaceBetweenWords = totalSpaces / (numberOfWords - 1);
                int extraSpaces = totalSpaces % (numberOfWords - 1);

                for (int j = start; j < i; j++) {
                    currentLine.append(words[j]);
                    if (j < i - 1) {
                        // Add spaces
                        int spacesToAdd = spaceBetweenWords + (j - start < extraSpaces ? 1 : 0);
                        for (int k = 0; k < spacesToAdd; k++) {
                            currentLine.append(" ");
                        }
                    }
                }
            }

            justifiedText.add(currentLine.toString());
        }

        return justifiedText;
    }
}
