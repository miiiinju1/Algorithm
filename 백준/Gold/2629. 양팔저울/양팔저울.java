import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] weights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i= 0;i<N;i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            max+=weights[i];
        }

        int dpMax = max*2;
        int[] dp = new int[dpMax+1];
        dp[max] = 1;
        dp[weights[0] + max] = 1;
        dp[max - weights[0]]= 1;
        for(int i = 1;i<N;i++) {
            int[] newDp = new int[dpMax + 1];
            for(int j= 0;j<=dpMax;j++) {
                if(dp[j]==0) continue;

                newDp[j] = 1;
                if(j-weights[i]>=0) {
                    newDp[j-weights[i]] = 1;
                }
                if(j+weights[i]<=dpMax) {
                    newDp[j+weights[i]] = 1;
                }
            }
            dp = newDp;
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0;i<M;i++) {
            int input = Integer.parseInt(st.nextToken())+max;


            if(input<=dpMax &&dp[input]==1) {
                bw.write("Y ");
            }
            else {
                bw.write("N ");
            }
        }
        bw.flush();bw.close();
    }


}
