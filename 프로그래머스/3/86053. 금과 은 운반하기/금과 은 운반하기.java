class Solution {
    
    static int[] gold;
    static int[] silver;
    static int[] weight;
    static int[] t;
    static int a,b;
    static boolean check(long time) {
        
        long goldSum = 0, silverSum = 0, wSum=0;
        
        for(int i= 0;i<gold.length;i++) {
            
            long times = time/(t[i]*2);
            if(time%(t[i]*2)>=t[i]) {
                times++;
            }
            
            goldSum += Math.min(times * weight[i], gold[i]);
            silverSum += Math.min(times * weight[i], silver[i]);
            wSum += Math.min(times * weight[i], gold[i] + silver[i]);
        }
        
        
        if(goldSum>=a && silverSum>=b && wSum >=a+b) {
            return true;
        }
        return false;
        
    }
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] time) {
        gold = g;
        silver = s;
        weight = w;
        t = time;
        this.a= a;
        this.b= b;
        
        long lo = 0, hi = 400000000000000L;
        
        while(lo+1<hi) {
            
            long mid = (hi-lo)/2 + lo;
            
            if(check(mid)) {
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