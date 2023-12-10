import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[31][31];

        Arrays.fill(dp[0], 1);
        for(int i=1;i<31;i++) {
            dp[i][i] = dp[i-1][i];
            for(int j = i+1;j<31;j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
  
        while(N!=0) {
            bw.write(dp[N][N]+"\n");
            N = Integer.parseInt(br.readLine());

        }
        bw.flush();bw.close();
    }
}
