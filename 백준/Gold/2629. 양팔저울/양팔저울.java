import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] weights;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        weights = new int[N];

        int[][] dp = new int[80001][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        dp[40000][N-1] = 1;
        dp[weights[N-1]+40000][N-1] = 1;
        dp[40000-weights[N-1]][N-1] = 1;
        for(int i = N-2;i>=0;i--) {
            for(int j= 0;j<=80000;j++) {
                if(dp[j][i+1]==0) continue;

                dp[j][i] = 1;
                if(j-weights[i]>=0) {
                    dp[j-weights[i]][i] = 1;
                }
                if(j+weights[i]<=80000) {
                    dp[j+weights[i]][i] = 1;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0;i<M;i++) {
            if(dp[Integer.parseInt(st.nextToken())+40000][0]==1) {
                bw.write("Y ");
            }
            else {
                bw.write("N ");
            }
        }
        bw.flush();bw.close();
    }
}
