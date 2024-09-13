import java.util.*;

class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");

        int maxLength = 0;
        for (String word : words) {
            maxLength = Math.max(word.length(), maxLength);
        }

        char[][] result = new char[maxLength][words.length];
        for (char[] chars : result) {
            Arrays.fill(chars, ' ');
        }

        for(int i = 0;i < words.length;i++) {
            String word = words[i];
            for(int j= 0;j<word.length();j++) {
                result[j][i] = word.charAt(j);
            }
        }

        return Arrays.stream(result)
            .map(ary -> new String(ary).stripTrailing())
            .toList();

    
    }
}