
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static char[] str;
    static int[][] dp;
    static char[] open = {'{', '[', '('};
    static char[] close = {'}', ']', ')'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        str = br.readLine().toCharArray();
        dp = new int[N][N];
        for(int i = 0;i<N;i++) {
            Arrays.fill(dp[i], -1);
        }
        int res = search(0, N - 1);
        if(res >= MOD) {
            System.out.printf("%05d", res % MOD);
            return;
        }
        System.out.println(res);

    }

    static int search(int lo, int hi) {

        if(lo > hi) {
            return 1;
        }

        if (dp[lo][hi] != -1) {
            return dp[lo][hi];
        }

        dp[lo][hi] = 0;

        for (int end = lo + 1; end <= hi; end += 2) {
            for (int i = 0; i < 3; i++) {
                if (str[lo] == open[i] || str[lo] == '?') {
                    if (str[end] == close[i] || str[end] == '?') {

                        int inner = search(lo + 1, end - 1);
                        int outer = search(end + 1, hi);
                        // 0 3
                        long mul = (long) inner * outer;
                        if (mul + dp[lo][hi] >= MOD) {
                            dp[lo][hi] = MOD + (int) ((mul + dp[lo][hi]) % MOD);
                        } else {
                            dp[lo][hi] += (int) mul;
                        }
                        // 0 1 2 3
                    }
                }



            }
        }

        return dp[lo][hi];

        //dp[lo][hi]는 lo부터 hi까지 몇 개나 나오는지 보는 거

        // lo로 부터 닫혀지는 문자열의 개수를 구하는 함수

    }
    static final int MOD = 100_000;

}
