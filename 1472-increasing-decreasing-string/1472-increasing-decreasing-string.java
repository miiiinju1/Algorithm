import java.util.*;

class Solution {
    public String sortString(String s) {

        int[] characters = new int[26];

        for (int i = 0; i < s.length(); i++) {
            ++characters[s.charAt(i) - 'a'];
        }
        StringBuilder sb = new StringBuilder(s.length());

        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; ++i) {
                if (characters[i] > 0) {
                    --characters[i];
                    sb.append((char)(i + 'a'));
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (characters[i] > 0) {
                    --characters[i];
                    sb.append((char)(i + 'a'));
                }
            }

        }

        return sb.toString();
    }
}