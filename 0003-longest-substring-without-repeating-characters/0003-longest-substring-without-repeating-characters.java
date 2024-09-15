class Solution {
    static int lengthOfLongestSubstring(String s) {

        int lo = 0;
        int hi = 1;
        char[] ary = s.toCharArray();
        int max = 1;
        int[] lastIndex = new int[128];
        Arrays.fill(lastIndex, -1);

        if(s.length()==0) {
            return 0;
        }
        lastIndex[ary[0]] = 0;

        // O(n)
        // a b c a
        // a 0
        // b 1
        // c 2

        // 이 떄 lo = 0, hi = 3

        // hi가 a였어요, 그러면 lastIndex[aIndex] = 0, lo = 0

        // a b c a ... a

        // a b c a 
        // lo = 1만들고

        // 다음 loop에서 b c, a가 들어가요
        while (hi < ary.length) {
            char now = ary[hi];


            // 포함된 경우를 따질 때는
            // lastIndex가 -1이 아니면서,  lo가 lastIndex보다 커야합니다.
            if (lastIndex[now]!=-1 && lastIndex[now] >= lo) {
                lo = lastIndex[now] + 1;
            }
            lastIndex[now] = hi++;
            // hi가 1이고, lo가 0이면 1이지 않나요?
            // hi가 넣어보려는 대상으로 삼아서

            // 현재까지 들어가있는게 lo ~ hi-1
            max = Math.max((hi-lo), max);
        }

        // 만약 다음 hi가 temp에 이미 있다면

        // lo에 있는 값을 temp에서 빼면서 lo를 증가시키기
        
        // 줄였을 때 경우가
                // temp .add(ary[hi]);
                // 다음으로 가야하지 않을까

            // 아직 lo + x = hi인 경우 
            
                // 아직 여유가 있으니깐 hi를 넣어보는데
                    // contains라면 다시 lo늘려보고

                    // 들어가도 되면 넣어보기


        // 반복은 hi가 < ary.length 까지만 반복하면 될 것 같아요

        return max;
    }
}