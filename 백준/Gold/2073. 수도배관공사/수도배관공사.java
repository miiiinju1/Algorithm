
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


//    static Pipe[] pipes;
    static int D,P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int[][] dp = new int[D+1][P];

        for(int j = 0;j<=D;j++) {
            Arrays.fill(dp[j], -1);
        }

//        st = new StringTokenizer(br.readLine());
        dp[0][0] = Integer.MAX_VALUE;
       for(int i = 0;i<P;i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for(int j = 0;j<=D;j++) {
                if(i-1>=0)
                if(dp[j][i-1]!=-1) {
                    dp[j][i] = Math.max(dp[j][i-1], dp[j][i]);
                    if(j+l<=D) {
                        dp[j + l][i] = Math.max(dp[j + l][i], Math.min(dp[j][i - 1], c));
                    }
                }
            }
        }


        System.out.println(dp[D][P-1]);
    }
}
