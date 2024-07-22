
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] ary = new int[N][2];
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ary[i][0] = x;
            ary[i][1] = y;
        }


        int[][][] dp = new int[N + 1][M + 1][K + 1];
        for(int i= 0;i<=N;i++)
            for(int j= 0;j<=M;j++)
                for(int k = 0;k<=K;k++)
                    dp[i][j][k] = -1;


        for(int i = 0;i<N;i++) {
            int m = ary[i][0];
            int n = ary[i][1];
            for(int j = 0;j<=M;j++) {
                for(int k = 0;k<K;k++) {
                    // 기존 거에서 찾거나
                    if (dp[i][j][k] != -1) {
                        dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k]);

                        if(j + m <=M && k + n <= K) {
                            
                            dp[i + 1][j + m][k + n] = Math.max(dp[i + 1][j + m][k + n], dp[i][j][k] + 1);
                        }
                    }
                }
            }

            // 그냥 쌩으로 하나
            if(m<=M && n <=K) {
                dp[i + 1][m][n] = Math.max(dp[i + 1][m][n], 1);
            }

        }
        int max = 0;
        for(int i= 0;i<=N;i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= K; k++) {
                    max = Math.max(max, dp[i][j][k]);
                }
            }
        }


        System.out.println(max);
        // 치즈 x, 감자튀김 y개

        // 개수가 최대화,

        // 배낭 문제 같은데



    }
}
