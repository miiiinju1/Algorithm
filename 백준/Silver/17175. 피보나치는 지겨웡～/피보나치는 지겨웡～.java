import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] dp;
    static long fibonacci(int n) { // 호출
        if (n < 2) {
            return dp[n];
        }
        if(dp[n]!=0) {
            return dp[n];
        }
        dp[n] = (1+fibonacci(n - 2) + fibonacci(n - 1))% 1000000007;
        return dp[n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n<2)
            dp = new long[3];
        else
            dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;

        System.out.println(fibonacci(n));
    }
}

