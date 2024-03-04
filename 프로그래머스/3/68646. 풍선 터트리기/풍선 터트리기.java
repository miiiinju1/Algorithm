import java.util.*;
class Solution {
    public int solution(int[] a) {
        if(a.length == 1) {
            return 1;
        }
        HashSet<Integer> count = new HashSet<>();
        int now = Integer.MAX_VALUE;

        for(int i = 0;i<a.length;i++) {
            now = Math.min(now, a[i]);
            count.add(now);
        }
        now = Integer.MAX_VALUE;
        for(int i = a.length-1;i>=0;i--) {
            now = Math.min(now, a[i]);
            count.add(now);
        }
    
        return count.size();
    }
}
