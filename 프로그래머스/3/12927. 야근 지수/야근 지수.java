import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int work : works) {
            pq.add(work);
        }
        
        while(n>0) {
            if(pq.isEmpty()) {
                break;
            }
            int now = pq.poll();
            
            if(now!=1) {
                pq.add(now-1);
            } 
            n--;
        }
        
        return pq.stream()
            .map(i->i*i)
            .mapToLong(i->i)
            .sum();
    }
}