

class Solution {

    static int[] silver;
    static int[] gold;
    static int[] weight;
    static int[] time;
    
    //ㅅㅂ 이걸 어케푸노
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
        
        return hi;
    }
}
