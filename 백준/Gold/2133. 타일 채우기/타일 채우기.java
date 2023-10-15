import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp;
        if(N<=6)
            dp= new long[7];
        else
            dp = new long[N+1];

        dp[1] = 0;
        dp[2] = 3;
        dp[3] = 0;
        dp[4] = 9 + 2;
        dp[5] = 0;
        dp[6] = 41;
        for(int i = 7;i<=N;i++)
        {
            dp[i] = dp[i-2]*4-dp[i-4];
        }
        System.out.println(dp[N]);
    }
}
