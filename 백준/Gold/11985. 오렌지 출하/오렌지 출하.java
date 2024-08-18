
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
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] ary = new int[N + 1];
    long[] dp = new long[N + 1];

    for(int i = 1;i<=N;i++) {
      ary[i] = Integer.parseInt(br.readLine());
    }

    Arrays.fill(dp, Long.MAX_VALUE);
    dp[0] = 0;
    int min;
    int max;
    for(int i = 1;i<=N;i++) {
      max = 0;
      min = Integer.MAX_VALUE;
      //LCS
      for (int j = 1; j <= M; j++) {
        if(i<j) break;
        max = Math.max(max, ary[i - j + 1]);
        min = Math.min(min, ary[i - j + 1]);
        dp[i] = Math.min(dp[i], dp[i - j] + K + (long) (max - min) * j);
      }
    }
    System.out.println(dp[N]);

  }
}
