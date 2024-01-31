class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        //10 ^ 9 * 10^5 * 4
        long lo = 0, hi = 400000000000000L;
        
        while(lo+1<hi) {
            long now = (hi-lo)/2+lo;
            
            long totalGold = 0;
            long totalSilver = 0;
            long total = 0;
            
            for(int i = 0;i<g.length;i++) {
                long count = now / (t[i]*2);
                
                if(now%(t[i]*2)>=t[i]) {
                    count++;
                }
                totalGold += Math.min(g[i], w[i]*count);
                totalSilver += Math.min(s[i], w[i]*count);
                total+= Math.min(w[i]*count, g[i]+s[i]);
            }
            if(totalGold>=a && totalSilver>=b&&total>=a+b) {
                hi = now;
            }else {
                lo = now;
            }
            
        }
        return hi;
    }
}