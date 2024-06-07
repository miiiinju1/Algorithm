
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long[][] dp;

    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             n = Integer.parseInt(st.nextToken());
             m = Integer.parseInt(st.nextToken());
            dp = new long[n+1][m+1];

            Arrays.fill(dp[0], 1L);

            for(int i = 1;i<=n;i++) {
                // dp[i][j] 까지

                // 만약 현재로 뒤에 뿌리는 거 -> dp[i][j] -> dp[i+1][j*2 ~ m] 이건 너무 많음

                // 따라서, 전에 껄 참조하기,

                //전에 꺼는 그 전까지의 결과를 저장해둬야함

                //dp[2][4] = 1일 때, 2일 때
                //dp [2][5] = 1일 때, 2일 떄
                for(int j= 1;j<=m;j++) {
                    dp[i][j] = dp[i - 1][j / 2] + dp[i][j - 1];
                }
            }

            bw.write(dp[n][m] + "\n");
        }
        bw.flush();bw.close();

    }
}
