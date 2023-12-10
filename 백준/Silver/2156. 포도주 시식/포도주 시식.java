import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] ary;
    static int N, max=0;

//    static void dfs(int start, int sum, int seq){
//        if(start == N) {
//            if(max<sum) {
//                max = sum;
//            }
//        }
//        for(int i = start;i<N;i++) {
//            if(seq==2) {
//                dfs(i+1,sum,0);
//            }
//            else {
//                dfs(i+1,sum+ary[i],seq+1);
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ary = new int[N];
        for(int i= 0;i<N;i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }
////        dfs(0,0,0);
////        System.out.println(max);
//
//        int result = ary[0]+ary[1];
//        int before = ary[0]+ary[1];

        if(N==1) {
            System.out.println(ary[0]);
            return ;
        }
        if(N==2) {
            System.out.println(ary[0]+ary[1]);
            return ;
        }


        int[][] dp = new int[3][N];

        dp[1][0] = ary[0];
        dp[0][1] = ary[0];
        dp[1][1] = ary[1];
        dp[2][1] = ary[0]+ary[1];

        for(int i = 2;i<N;i++) {
            dp[1][i] = ary[i] + Math.max(dp[1][i-2],dp[0][i-1]);//Math.max(dp[1][i - 1], dp[1][i - 2]);
            dp[2][i] = ary[i] + dp[1][i-1];
            dp[0][i] = Math.max(dp[2][i-1],Math.max(dp[1][i-1],dp[0][i-1]));

        }

//        for(int i= 0;i<3;i++) {
//            for(int j= 0;j<N;j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(Math.max(dp[0][N - 1], Math.max(dp[1][N - 1], dp[2][N - 1])));


    }
}

//3
//10
//20
//30