import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        int[][] goods = new int[N][2];

        for(int i= 0;i<N;++i) {
            st = new StringTokenizer(br.readLine());
            goods[i][0] = Integer.parseInt(st.nextToken());
            goods[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[K+1];
  


        int max = 0;

        for (int[] good : goods) {
            for (int i = K; i >= good[0]; --i) {
                dp[i] = Math.max(dp[i - good[0]] + good[1], dp[i]);
            }
        }

        for (int i : dp) {
            max = Math.max(max, i);
        }
        System.out.println(max);
    }

}
