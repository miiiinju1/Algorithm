import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] ary = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1;i<=N;i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }


        int[][] dp = new int[N+1][5001];


        if (ary[1] > 0 || ary[N] > 0) {
            System.out.println(0);
            return ;
        }
        dp[1][0] = 1;
        ary[N] = 0;

        for (int i = 2; i <= N; i++) {
            if (ary[i] == -1) {
                dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                for (int k = 1; k < 5000; k++) {
                    dp[i][k] = ((dp[i - 1][k - 1] + dp[i - 1][k]) % MOD +dp[i - 1][k + 1]) % MOD;
                }
            }
            else {
                if (ary[i] == 0)
                    dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
                else
                    dp[i][ary[i]] = ((dp[i - 1][ary[i] - 1] + dp[i - 1][ary[i]]) % MOD +dp[i - 1][ary[i] + 1]) % MOD;
            }
        }

        System.out.println(dp[N][0]);


    }
}

// -1 2 1

// 0 0 0.

//만약 0 선택하면

// 0 1 0 이니깐 절대로 2가 될 수 없는 구조


// 0 0 0에서 0 1 0 가능



//
// -1 0 0 0 0 0
//->
// -1 0 1 2 1 0

// 0 0 0 0 0 0
// 0 1 1 1 1 0
// 0 1 2 2 1 0

// 0 1 1 1 1 0
// 0 -1 1 2 1 0

// -1 1 2 2 1 0


