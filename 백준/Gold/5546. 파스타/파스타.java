import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[N+1][3][2];

        Map<Integer, Integer> pre = new HashMap<>();
        for(int i= 0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            pre.put(a, b);
        }

        if (!pre.containsKey(1)) {
            dp[1][0][0] = 1;
            dp[1][1][0] = 1;
            dp[1][2][0] = 1;
        }
        else {
            dp[1][pre.get(1)][0] = 1;
        }
        for(int i = 2;i<=N;i++) {
            if (pre.containsKey(i)) {
                int pasta = pre.get(i);
                for(int k = 0;k<3;k++) {
                    if(k==pasta) {
                        dp[i][pasta][1] = dp[i-1][pasta][0];
                        continue;
                    }
                    dp[i][pasta][0] = (dp[i][pasta][0]+dp[i - 1][k][0])%10000;
                    dp[i][pasta][0] =  (dp[i][pasta][0] +dp[i - 1][k][1])%10000;
                }
                continue;
            }

           for(int j= 0;j<3;j++) {

               for(int k = 0;k<3;k++) {
                   //같은 파스타를 먹을 때는 날짜 증가 시켜야하니깐
                   if(k==j) {
                       dp[i][j][1] = dp[i-1][j][0];
                       continue;
                   }
                   // 다른 파스타 먹을 때는 다른 경우 그대로 더하면 되니깐
                   dp[i][j][0] = (dp[i][j][0]+dp[i-1][k][0])%10000;
                   dp[i][j][0] = (dp[i][j][0]+dp[i-1][k][1])%10000;
               }
           }

        }

        int sum = 0;
        for(int j = 0;j<3;j++) {
            for (int k = 0; k < 2; k++) {
                sum = (sum + dp[N][j][k]) % 10000;
            }
        }
//        for(int i= 1;i<=N;i++) {
//            System.out.print(i+"일 = ");
//            for(int j = 0;j<3;j++) {
//                System.out.print("["+j+":");
//                for(int k = 0;k<2;k++) {
//                    System.out.print(dp[i][j][k]+",");
//                }
//                System.out.print("]");
//            }
//
//            System.out.println();
//        }
        System.out.println(sum);





    }
}
