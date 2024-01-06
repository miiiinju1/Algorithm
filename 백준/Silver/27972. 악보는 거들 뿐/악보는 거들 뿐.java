import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int[] ary = new int[M];
        int[] dp = new int[M];
        int[] dp2 = new int[M];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i= 0;i<M;i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        dp2[0] = 1;
        for(int i= 1;i<M;i++) {
            if(ary[i]>ary[i-1]) {
                dp[i] = dp[i-1]+1;
                dp2[i] = 1;
            }else if(ary[i]<ary[i-1]){
                dp[i] = 1;
                dp2[i] = dp2[i-1]+1;
            }
            else {
                dp[i] = dp[i-1];
                dp2[i] = dp2[i-1];
            }
        }
        int max = 0;

        for(int i= 0;i<M;i++) {
           max = Math.max(max, Math.max(dp[i], dp2[i]));
        }

        System.out.println(max);
    }
}
