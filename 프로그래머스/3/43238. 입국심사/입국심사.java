class Solution {

    public static int[] times;
    public static int n;
    public static long check(long time){
        long count = 0;

        for(int i= 0;i<times.length;i++)
        {
            count += time/times[i];
            
        }
        
        //1 10이 있을 때 n이 9이면
        //위 방법으로 하면 10이 걸림
        //1만 계속하면 9만에 끝나는데
        
      System.out.println("time : "+time+" : "+count);
        return count;    
        
    }
    public long solution(int n_p, int[] t) {
        times = t;
        n= n_p;
        long lo = (long)0;
        long hi = (long) 1000000000*(long)1000000000 +1;
       // long answer = hi;
        long mid=(long)-1;
        while(lo<hi)
        {
            mid = (lo+hi)/2;
            
            if(check(mid)<n)
                lo = mid+1;
            else
            {
                hi = mid;
                //answer = mid;
            }
            
            
            
            //System.out.println(mid);
            
            
            
            
            
        }
        
        
        
        
        
        
        
        
        
        return lo;
    }
}



// n이 4, times[7, 4]일때 7 8 마지막 사람이 4를 선택하는 게 이득  7 4 -> 4선택 7 8 -> 8선택 

// 최대 시간 최소 시간뭐 해서 이분탐색 하면서 그 시간 내에 검사가 완료되는지 

//같거나 그 시간 보다 적으면 hi를 줄이고

//더 걸리면 lo= mid+1

//남은 시간 + 끝나는 시간 가장 적은걸로 그리디??
