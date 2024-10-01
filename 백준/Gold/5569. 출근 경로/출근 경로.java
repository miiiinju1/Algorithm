
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MOD = 100000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[4][h][w];

        // 오른쪽은 0, 방향 바꾼 오른쪽은 1, // 아래쪽은 2, 방향 바꾼 아래쪽은 3
        dp[0][0][0] = 0;
        dp[0][0][1] = 1;
        dp[2][1][0] = 1;

        // 1은 방향 전환해서 간 거

        // 0은 직진한 거

        //0 0 -> h-1, w-1로 가면 되는데


        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                // 방향 바꾸는 경우
                // 오른쪽으로 자유로운 경우
                if (dp[0][i][j] != 0) {

                    // 오른쪽 그대로
                    if (j + 1 < w) {
                        dp[0][i][j + 1] = (dp[0][i][j] + dp[0][i][j + 1]) % MOD;
                    }
                    // 아래 쪽으로
                    if (i + 1 < h) {
                        dp[3][i + 1][j] = (dp[3][i + 1][j] + dp[0][i][j] ) % MOD;
                    }
                }

                // 아래쪽으로 자유로운 경우
                if (dp[2][i][j] != 0) {
                    // 아래
                    if (i + 1 < h) {
                        dp[2][i + 1][j] = (dp[2][i + 1][j] + dp[2][i][j] ) % MOD;
                    }
                    // 오른쪽 쪽으로
                    if (j + 1 < w) {
                        dp[1][i][j + 1] = (dp[1][i][j + 1] + dp[2][i][j] ) % MOD;
                    }
                }

                // 오른쪽으로 그대로 가야함
                if (dp[1][i][j] != 0) {
                    if (j + 1 < w) {
                        dp[0][i][j + 1] = (dp[0][i][j + 1] + dp[1][i][j]) % MOD;
                    }
                }

                // 아래쪽 가야함
                if (dp[3][i][j] != 0) {
                    if (i + 1 < h) {
                        dp[2][i+1][j] = (dp[2][i+1][j] + dp[3][i][j]) % MOD;
                    }
                }

                // 그대로 직진하는 경우
            }
        }
        System.out.println(answer(dp[0][h - 1][w - 1], dp[1][h - 1][w - 1], dp[2][h - 1][w - 1],
            dp[3][h - 1][w - 1]));
//
//        System.out.println(
//            "answer(dp[0][h - 1][w - 1], dp[1][h - 1][w - 1], dp[2][h - 1][w - 1], dp[3][h - 1][w - 1]) = "
//                + answer(dp[0][h - 1][w - 1], dp[1][h - 1][w - 1], dp[2][h - 1][w - 1],
//                dp[3][h - 1][w - 1]));
//        System.out.println(dp[0][h - 1][w - 1]);
//        System.out.println(dp[1][h - 1][w - 1]);
//        System.out.println(dp[2][h - 1][w - 1]);
//        System.out.println(dp[3][h - 1][w - 1]);

        //

        // 남북방향 도로 w개, 동서 방향 도로 h개,

        //

        /**


         세로는 왼쪽부터 12345
         가로는 남쪽 부터 1,2,3,4,5



         왼쪽 아래가 1,1 -> w, h

         방향 전환 이후에는 다음 교차로는 계속 직진해야함


         */
    }


    static int answer(int a, int b, int c, int d) {
        return (((a % MOD + b % MOD) % MOD + c % MOD) % MOD + d % MOD) % MOD;
    }
}
