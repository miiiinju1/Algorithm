
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;

    static final int[][] grade = {
            {10, 8, 7, 5, 0, 1},
            {8, 6, 4, 3, 0, 1},
            {7, 4, 3, 2, 0, 1},
            {5, 3, 2, 2, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0}
    };

    static int[] map;
    //retry
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N*M];
        for(int i= 0;i<N;i++) {
            final String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i * M + j] = (str.charAt(j) - 'A');
            }
        }

        int[][] dp = new int[N * M + 1][1 << M];
        dp[0][0] = 0;

        for (int i = 0; i < N * M; i++) {
            for (int mask = 0; mask < (1 << M); mask++) {
//                if (dp[i][mask] == 0) continue;

                //만약 현재 안 비어있으면
                if((mask & 1) > 0) {
                    dp[i + 1][mask>>1] = Math.max(dp[i][mask], dp[i + 1][mask>>1]);
                }
                else {
                    //가로방향
                    if (i % M != M - 1 && (mask & 2) == 0) {
                        //현재 i, 오른쪽 i+1
                        dp[i + 2][mask >> 2] = Math.max(dp[i][mask] + grade[map[i]][map[i+1]], dp[i + 2][mask >> 2]);
                    }

                    //세로
                    if (i / M != N-1) {
                        //현재 i, 아래쪽 i+M
                        dp[i + 1][(mask >> 1) + (1 << (M - 1))] = Math.max(dp[i][mask] + grade[map[i]][map[i + M]], dp[i + 1][(mask >> 1) + (1 << (M - 1))]);
                    }
                    dp[i + 1][mask>>1] = Math.max(dp[i][mask], dp[i + 1][mask>>1]);

                }
            }
        }
        System.out.println(dp[N * M][0]);


    }
}
