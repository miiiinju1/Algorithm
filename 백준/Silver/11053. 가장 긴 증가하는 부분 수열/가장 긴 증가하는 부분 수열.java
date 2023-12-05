
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] ary = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        dp[0]=1;
        for(int i =1;i<N;i++){
            for(int j = i-1;j>=0;j--) {
                if(ary[j]<ary[i]&&dp[i]<dp[j]+1) {
                    dp[i] = dp[j]+1;
                }

            }
            if(dp[i]==0)
                dp[i]=1;
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}