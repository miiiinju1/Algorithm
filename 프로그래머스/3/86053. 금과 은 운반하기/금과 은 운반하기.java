

class Solution {

    static int[] silver;
    static int[] gold;
    static int[] weight;
    static int[] time;
    
    static boolean Check(long now,int a, int b) {
        long totalGold=0;
        long totalSilver=0;
        long total = 0;
        for(int i= 0;i<weight.length;i++) {
            long count = now/(time[i]*2);
            
            if(now%(time[i]*2)>=time[i]) {
                count++;
            }
            
            totalGold+= Math.min(gold[i],weight[i]*count);
            totalSilver+= Math.min(silver[i],weight[i]*count);
            total+= Math.min(gold[i]+silver[i], weight[i]*count);
        }
        if(a<=totalGold && b <= totalSilver && a+b <= total) {
            return true;
        }
        return false;
        
        
    }
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        silver = s;
        gold = g;
        weight = w;
        time = t;
        long lo = 0,hi = 1000000000000001L;
        while(lo+1<hi) {
            long mid = (hi-lo)/2 + lo;
            
            if(Check(mid,a,b)) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }
        
        long answer = hi;
        return answer;
    }
}

//가장 먼저
// 하나씩 몇 번에 갈 수 있는지 모두 카운트

//t순으로 정렬, w순으로 정렬???

//w/t순으로 정렬??

// 70 + 

//70 + 7

//a부터 다 하고????