import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

        ary = new List[N];
        for(int i = 0;i<N;i++) {
            ary[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            ary[i].add(0);
            while(st.hasMoreTokens()) {
                ary[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] dp = new int[N][H+1];
        for (Integer i : ary[0]) {
            dp[0][i] += 1;
        }

        for(int i = 1;i<N;i++) {
            for(int j = 0;j<=H;j++) {
                if(dp[i-1][j]!=0) {
                    for (Integer num : ary[i]) {
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
