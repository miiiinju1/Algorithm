
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[2][N + 1];


        dp[0][a] = 1;
        dp[1][0] = 1;
        if(b<=N)
            dp[0][b] = -1;
        if(d<=N)
            dp[1][d] = -1;

        for (int i = 1; i <= N; i++) {
            dp[1][i] = (dp[1][i - 1]+dp[1][i])%MOD;
            dp[0][i] = (dp[0][i - 1]+dp[0][i])%MOD;

            if (dp[0][i] > 0) {
                dp[1][i] = (dp[0][i]+dp[1][i])%MOD;
                if(i+a <=N) {
                    dp[0][i + a] = (dp[0][i] + dp[0][i+a])%MOD;
                }
                if(i+b<=N) {
                    dp[0][i + b] = (dp[0][i + b] - dp[0][i]) % MOD;
                    if(dp[0][i+b]<0) {
                        dp[0][i+b]+=MOD;
                    }
                }
                if(i+d<=N) {
                    dp[1][i + d] = (dp[1][i + d] - dp[0][i]);
                    if(dp[1][i + d]<0) {
                        dp[1][i + d]+=MOD;
                    }
                }
            }
//            for(int j= 0;j<=N;j++) {
//                System.out.println(dp[0][j] + " " + dp[1][j]);;
//            }
//            System.out.println();

        }


        System.out.println(dp[1][N]);

    }
}
