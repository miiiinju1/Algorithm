
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    // 2^ 16 = 약 10만개
    static long[][] dp;

    static boolean[] visited;
    static int[] ary;
    static int N, K;

    static long search(int index, int used, int lastI) {
        if (index == N) {
            return 1;
        }
//        System.out.println("used = " + used);

        if (lastI != -1 && dp[used][lastI] != -1) {
            return dp[used][lastI];
        }
        long temp = 0;

        for (int i = 0; i < N; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                if(lastI==-1) {
                    temp += search(1, 1 << (N - i - 1), i);
                } else if (Math.abs(ary[lastI] - ary[i]) > K) {
//                    System.out.println(ary[lastI] + " " + ary[i]);
                    temp += search(index + 1, used | 1 << (N - i - 1), i);
                }
                visited[i] = false;
            }
        }

        if (lastI != -1) {
            dp[used][lastI] = temp;
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[(1 << 16) - 1][N];



        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < N; ++j) {
                dp[i][j] = -1;
            }
        }
        ary = new int[N];
        for (int i = 0; i < N; ++i) {
            ary[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N];
        System.out.println(search(0, 0, -1));


    }

}
