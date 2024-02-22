import java.util.*;
class Solution {
    static class Car {
        int start,end;
        public Car(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int solution(int[][] routes) {
        
        PriorityQueue<Car> pq = new PriorityQueue<>((o1,o2) -> {
                                    
                                    if(o1.start==o2.start) {
                                        return Integer.compare(o1.end,o2.end);
                                    }
                                    return Integer.compare(o1.start,o2.start);
                                } );
        
        for(int[] route : routes) {
            int start = route[0];
            int end = route[1];
            pq.add(new Car(start,end));
        }
        
        int nowStart = -1, nowEnd = -1;
        int count = 0;
        
        while(!pq.isEmpty()) {
            //하나 꺼내서
            Car car = pq.poll();
            //만약 nowStart랑 nowEnd 사이에서 시작하면
            if(car.start>=nowStart && car.start<=nowEnd) {
                //꺼낸거 end가 더 크면 end 유지
                if(car.end>=nowEnd) {
                    
                }
                else {
                //아니면 end르작은 쪽으로
                    nowEnd = car.end;   
                }
            }
            //사이에서 시작 안 하면
            else {
                // 카운트 증가
                count++;
                
                //nowStart nowEnd갱신
                nowStart = car.start;
                nowEnd = car.end;
            }
            
        }
        
        return count;
    }
}