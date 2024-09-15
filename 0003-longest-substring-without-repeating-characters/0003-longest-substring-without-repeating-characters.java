class Solution {
    static int lengthOfLongestSubstring(String s) {

        int lo = 0;
        int hi = 1;
        char[] ary = s.toCharArray();
        Set<Character> temp = new HashSet<>();
        if(s.length()==0) {
            return 0;
        }
        temp.add(ary[0]);

        // a b

        /// a a 

        // 

        // a b c a b c

        int max = 1;
        while (hi < ary.length) {

            char now = ary[hi];

            if(temp.contains(now)) {
                // lo를 늘리면서 temp에서 뺴기
                temp.remove(ary[lo++]);
            } else {
                // 들어가도 되면 hi넣고 hi증가키기
                temp.add(now);
                ++hi;
            }

            max = Math.max(temp.size(), max);

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