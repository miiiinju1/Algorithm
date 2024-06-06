
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] dp;
    static final int MOD = 9901;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N * M + 1][1 << M];

        dp[0][0] = 1;

        for (int i = 0; i < N * M; i++) {

            for (int mask = 0; mask < (1 << M); mask++) {

                //0이 현재 칸,
                //그러면 M-1번째 칸이 마지막칸임
                if(dp[i][mask]==0) continue;

                if((mask & 1) > 0) {
                    dp[i + 1][mask >> 1] = (dp[i + 1][mask >> 1] + dp[i][mask]) % MOD;
                }
                else {
                    //가로로 채워보기
                    // 오른쪽 끝 줄 아니면
                    if (i % M != M - 1) {
                        // 현재칸은 비어있고,
                        // 오른쪽 칸 비어있으면
                        if((mask & 2) == 0) {
                            dp[i + 2][mask >> 2] = (dp[i + 1][mask >> 2] + dp[i][mask]) % MOD;
                        }
                    }
                    //세로로 채워보기
                    //맨 아랫줄 아니면
                    if (i / M != N - 1) {
                        dp[i + 1][(mask >> 1) + (1 << (M - 1))] = (dp[i + 1][(mask >> 1) + (1 << (M - 1))] + dp[i][mask]) % MOD;
                    }
                }
            }

        }

        System.out.println(dp[N*M][0]);


    }


}
