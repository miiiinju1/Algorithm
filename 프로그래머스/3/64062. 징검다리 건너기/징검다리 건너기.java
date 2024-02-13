import java.util.*;

class Solution {
    static class Value implements Comparable<Value> {
        int index, value;
        public Value(int i, int v) {
            this.index = i;
            this.value = v;
        }
        @Override
        public int compareTo(Value v) {
            return Integer.compare(v.value,this.value);
        }
    }
    
    public int solution(int[] stones, int k) {
        
        int min = Integer.MAX_VALUE;
        
        PriorityQueue<Value> pq = new PriorityQueue<>();
        for(int i= 0;i<k;i++) {
            pq.add(new Value(i,stones[i]));           
        }
        
        min = Math.min(min,  pq.peek().value);
        
        for(int i = k;i<stones.length;i++) {
            pq.add(new Value(i,stones[i]));
            while(!pq.isEmpty()) {
                if(pq.peek().index>i-k) {
                    min = Math.min(min,  pq.peek().value);
                    break;
                }
                else {
                    pq.poll();
                }
            }
        }
    
        int answer = min;
        return answer;
    }
}