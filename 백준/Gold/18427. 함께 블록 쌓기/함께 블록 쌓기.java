import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] ary;

    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
         H = Integer.parseInt(st.nextToken());

         int[][] ary = new int[N][M+1];
        for(int i = 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            ary[i][j++]=0;
            while(st.hasMoreTokens()) {
                ary[i][j++]=Integer.parseInt(st.nextToken());
            }
            for(;j<=M;j++) {
                ary[i][j] = -1;
            }
        }

        int[][] dp = new int[N][H+1];
        for (int i = 0;i<=M;i++) {
            if(ary[0][i]==-1)
                break;
            dp[0][ary[0][i]] += 1;
        }

        for(int i = 1;i<N;i++) {
            for(int j = 0;j<=H;j++) {
                if(dp[i-1][j]!=0) {
                    for(int t = 0;t<=M;t++) {
                        int num = ary[i][t];
                        if(num==-1)
                            break;
                        if(j+num<=H) {
                            dp[i][j+num] = (dp[i][j+num]+dp[i-1][j])%10007;
                        }
                    }
                }
            }
        }

        System.out.println(dp[N-1][H]);

    }
}
