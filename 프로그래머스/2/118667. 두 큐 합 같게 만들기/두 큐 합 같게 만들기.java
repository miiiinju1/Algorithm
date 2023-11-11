import java.util.*;

class Solution {
    static Queue<Long> queue1 = new LinkedList<>();
    static Queue<Long> queue2 = new LinkedList<>();
    static long queueSum1 = 0L;
    static long queueSum2 = 0L;
    
    static int search(int escape) {
        for(int i = 0;i<escape;i++) {
            Long temp;
            if(queueSum1>queueSum2) {
             temp = queue1.poll();
             queueSum1-=temp;
             queueSum2+=temp;
             queue2.add(temp);
              }
           else if(queueSum2>queueSum1) {
              temp = queue2.poll();
               queueSum2-=temp;
              queueSum1+=temp;
              queue1.add(temp);
             
          }
            else
                return i;
        }
        return -1;
        
    }
    public int solution(int[] q1, int[] q2) {
        
        for(int i: q1) {
            queue1.add((long)i);
            queueSum1+=i;
        }
        for(int i: q2) {
            queue2.add((long)i);
            queueSum2+=i;
        }
        
        if((queueSum1+queueSum2)%2!=0)
            return -1;
        
        return search(queue1.size()*3);
    }
}

//14


// 16