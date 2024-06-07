
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            if(M==0&&N==0) {
                break;
            }
            int[][][] dp = new int[M][N][2];
            int[][] map = new int[M][N];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //0은 안 고른 거 1은 고른 거
            for(int i = 0;i<M;i++) {
                dp[i][0][1] = map[i][0];
            }

            for(int i = 0;i<M;i++) {
                for (int j = 1; j < N; j++) {
                    dp[i][j][0] = Math.max(dp[i][j - 1][1], dp[i][j - 1][0]);
                    dp[i][j][1] = dp[i][j - 1][0] + map[i][j];
                }
            }

//            for(int i= 0;i<M;i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(dp[i][j][0] + ":"+dp[i][j][1]+" ");
//                }
//                System.out.println();
//            }

            int[] dpMax = new int[M];
            for(int i =0;i<M;i++) {
                dpMax[i] = Math.max(dp[i][N - 1][0], dp[i][N - 1][1]);

            }
            int[][] result = new int[M][2];

            result[0][1] = dpMax[0];

            for(int j = 1;j<M;j++) {
                result[j][0] = Math.max(result[j-1][1], result[j-1][0]);
                result[j][1] = result[j-1][0] + dpMax[j];
            }

            bw.write(Math.max(result[M - 1][0], result[M - 1][1]) + "\n");





        }

        bw.flush();
        bw.close();


    }
}
