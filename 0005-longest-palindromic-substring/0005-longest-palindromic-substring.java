import java.util.*;

class Solution {
    public String longestPalindrome(String s) {
        char[] ary = s.toCharArray();

        int max = 0;
        String maxStr = null;
        // 중앙 홀수일 경우로
        for(int i= 0;i<ary.length;i++) {
            Deque<Character> temp = new ArrayDeque<>();

            temp.add(ary[i]);

            for(int j= 1;j<ary.length;j++) {
                int left = i-j;
                int right = i+j;
                if(left < 0) break;
                if(right >= ary.length) break;

                if(ary[left]!=ary[right]) break;

                temp.addFirst(ary[left]);
                temp.addLast(ary[right]);
            }

            if(max < temp.size()) {
                max = temp.size();
                maxStr = temp.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(""));
            }

        }
        // 중앙 짝수일 경우로
        for(int i= 0;i<ary.length-1;i++) {
            Deque<Character> temp = new ArrayDeque<>();

            if(ary[i] != ary[i+1]) continue;
            temp.add(ary[i]);
            temp.add(ary[i+1]);


            for(int j= 1;j<ary.length;j++) {
                int left = i-j;
                int right = i+j+1;

                if(left < 0) break;
                if(right >= ary.length) break;

                if(ary[left]!=ary[right]) break;

                temp.addFirst(ary[left]);
                temp.addLast(ary[right]);
            }

            if(max < temp.size()) {
                max = temp.size();
                maxStr = temp.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(""));
            }

        }

        return maxStr;

    }
}