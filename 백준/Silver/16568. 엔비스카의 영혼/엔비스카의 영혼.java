
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());


        int[] dp = new int[N + 1];
        for(int i= 0;i<=N;i++) {
            dp[i] = i;
        }

        for (int i = 0; i < N; i++) {

            // a만큼
            if (i + a + 1 <= N) {
                dp[i + a + 1] = Math.min(dp[i + a + 1], dp[i] + 1);
            }
            //b만큼
            if (i + b+ 1 <= N) {
                dp[i + b + 1] = Math.min(dp[i + b + 1], dp[i] + 1);
            }
        }
        System.out.println(dp[N]);



    }
}
