
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 첫번째와 마지막은 달라야함
        int[][] cost = new int[N][3];
        for(int i = 0;i<N;i++) {
            var st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }


        int min = Integer.MAX_VALUE;
        int init = Integer.MAX_VALUE / 2;
        for (int start = 0; start < 3; start++) {
            int[][] dp = new int[N][3];
            for(int i = 0;i<N;i++) {
                for(int j= 0;j<3;j++) {
                    dp[i][j] = init;
                }
            }
            dp[0][start] = cost[0][start];
            for (int i = 1; i < N; i++) {

                for (int j = 0; j < 3; j++) {
                    for (int z = 0; z < 3; z++) {
                        if (j == z) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][z] + cost[i][j]);
                    }

                }
            }

            for(int i = 0;i<3;i++) {
                if(i==start)continue;
                min = Math.min(min, dp[N - 1][i]);
//                System.out.print(dp[N - 1][i] + " ");
            }

        }
            System.out.println(min);

    }
}
