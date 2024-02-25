import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        //O(n) 풀이 -> 시간초과
        
        int W = 2*w+1;
        int nowStart = 1;
        int count = 0;
    
        for(int now : stations) {
            if(nowStart<now-w) {
                int temp = now-w-nowStart;
                count+=(temp/W);
                if(temp%W!=0) {
                    count++;
                }
            }
            nowStart = now+w+1;
        }
            
        if(nowStart<=n) {
             int temp = n-nowStart+1;
                count+=(temp/W);
                if(temp%W!=0) {
                    count++;
                }
        }

        return count;
    }
}