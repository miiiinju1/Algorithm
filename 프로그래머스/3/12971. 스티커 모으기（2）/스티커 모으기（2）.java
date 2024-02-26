class Solution {
    
    static int[] visited;
    static int[] map;
    static int max = 0;
   
    public int solution(int sticker[]) {
        int[] dp = new int[sticker.length];
        int[] dp2 = new int[sticker.length];
        dp[0] = sticker[0];
        if(sticker.length>=2) {
            dp[1] = sticker[0];
            dp2[1] = sticker[1];
        }
        for(int i= 2;i<dp.length-1;i++) {
            dp[i] = Math.max(sticker[i]+dp[i-2], dp[i-1]);
        }
        if(sticker.length>=2) 
            dp[dp.length-1] = dp[dp.length-2];
        for(int i= 2;i<dp.length;i++) {
            dp2[i] = Math.max(sticker[i]+dp2[i-2], dp2[i-1]);
        }

        return Math.max(dp2[sticker.length-1],dp[sticker.length-1]);
    }
}