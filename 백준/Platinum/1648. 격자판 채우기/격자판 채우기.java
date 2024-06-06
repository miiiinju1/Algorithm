
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] dp;
    static int MOD = 9901;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N*M+1][1<<M];

        dp[0][0] = 1;

        for (int i = 0; i < N * M; i++) {

            for (int bit = 0; bit < 1<< M; bit++) {
                if(dp[i][bit]==0) continue;

                // 현재 칸이 채워져있는 게 있으면
                if((bit & 1)==1) {

                    // 그대로 옮기기
                    dp[i + 1][bit >> 1] = (dp[i + 1][bit >> 1] + dp[i][bit]) % MOD;
                }
                else {
                    // 세로로 타일을 놓는 경우
                    if (i / M != N - 1) {
                        dp[i + 1][(bit >> 1) | (1 << (M - 1))] =
                                (dp[i + 1][(bit >> 1) | (1 << (M - 1))] + dp[i][bit]) % MOD;
                    }
                    // 가로로 타일을 놓는 경우
                    if (i % M != M - 1 && (bit & 2) == 0) {
                        dp[i + 2][bit >> 2] =
                                (dp[i + 2][bit >> 2] + dp[i][bit]) % MOD;
                    }
                }



            }

        }

        System.out.println(dp[N*M][0]);



    }


}
