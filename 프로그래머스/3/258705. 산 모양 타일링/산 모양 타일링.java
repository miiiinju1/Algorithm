class Solution {
    public int solution(int n, int[] tops) {
        int MOD = 10007;
        int[][] dp = new int[n+1][2];
        dp[0][1] = 1;
        for(int i= 1;i<=n;i++) {
            if(tops[i-1]==0) {
                dp[i][0] = (dp[i-1][0]%MOD+dp[i-1][1]%MOD)%MOD;
                dp[i][1] = (dp[i-1][1]*2%MOD+dp[i-1][0]%MOD)%MOD;
            }
            else {
                dp[i][0] = (dp[i-1][0]%MOD+dp[i-1][1]%MOD)%MOD;
                dp[i][1] = (dp[i-1][1]*3%MOD+dp[i-1][0]*2%MOD)%MOD;
            }
        }
        int answer = (dp[n][0]+dp[n][1])%MOD;
        return answer;
    }
}