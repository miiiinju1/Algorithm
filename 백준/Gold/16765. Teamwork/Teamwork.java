
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] cows = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }

        long[][] dp = new long[K + 1][N + 1];

        long max = 0;
        for (int i = 1; i <= N; i++) {

            long nowMax = 0;
            long maxS = cows[i];

            for (int k = 1; k <= K; k++) {
                if (i - k < 0) {
                    break;
                }
                int beforeIndex = i - k;

                maxS = Math.max(cows[beforeIndex+1], maxS);
                long totalS = maxS * k;

//                System.out.println("totalS = " + totalS);
                long from = dp[0][beforeIndex];
                dp[k][i] = Math.max(dp[k][i], from + totalS);
                nowMax = Math.max(nowMax, dp[k][i]);
            }
            dp[0][i] = nowMax;
            max = Math.max(max, nowMax);

        }
//        for(int i = 1;i<=K;++i) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(max);


    }

}
