import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[2][N];
        int[] ary = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (ary[i] > ary[j]) {
                    dp[0][i] = Math.max(dp[0][i], dp[0][j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (ary[i] > ary[j]) {
                    dp[1][i] = Math.max(dp[1][i], dp[1][j] + 1);
                }
            }
            max = Math.max(dp[1][i] + dp[0][i] - 1, max);
        }
        System.out.println(max);

    }
}
