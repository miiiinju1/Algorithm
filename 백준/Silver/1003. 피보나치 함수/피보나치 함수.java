import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static class Pair {
        long first;
        long second;

        Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }

    static final int MAX = 100;
    static Pair[] dp = new Pair[MAX];

    static Pair solve(int x) {
        if (dp[x].first != -1) return dp[x];
        if (x == 0) return new Pair(1, 0);
        if (x == 1) return new Pair(0, 1);
        Pair r1 = solve(x - 1);
        Pair r2 = solve(x - 2);
        dp[x] = new Pair(r1.first + r2.first, r1.second + r2.second);
        return dp[x];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < MAX; i++) {
            dp[i] = new Pair(-1, -1);
        }
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            Pair res = solve(n);
            System.out.println(res.first + " " + res.second);
        }
        sc.close();
    }
}
