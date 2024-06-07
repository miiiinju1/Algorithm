
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static long[][] dp;

    static int n,m;
    static long search(int index, int start) {
        if (start > m) {
            return 0;
        }

        if (index == n) {
            return 1;
        }
        // 이미 계산된 값이 있는 경우
        if (dp[index][start] != -1) {
            return dp[index][start];
        }

        long count = 0;
        for (int i = start; i <= m; i++) {
            count += search(index + 1, i * 2);
        }
        // 계산된 결과 저장
        dp[index][start] = count;
        return count;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             n = Integer.parseInt(st.nextToken());
             m = Integer.parseInt(st.nextToken());
            dp = new long[n+1][m+1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[0], 1L);
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j / 2] + dp[i][j - 1];
                }
            }
            bw.write(dp[n][m] + "\n");
        }
        bw.flush();bw.close();

    }
}
