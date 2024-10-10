
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<int[]> objects = new ArrayList<>(1_000_000);

        int[] limit = new int[N + 1];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            limit[i + 1] = K;
            int value = V;
            int satisfaction = C;

            int totalCount = 0;
            for (int j = 1; ; j *= 2) {

                if (totalCount + j > K) {
                    int count = K - totalCount;
                    if(count==0) {
                        break;
                    }
                    objects.add(new int[]{count * V, count * C});
                    break;
                }
                objects.add(new int[]{value, satisfaction});
                value *= 2;
                satisfaction *= 2;
                totalCount += j;
            }
        }
        

        int[] dp = new int[M + 1];

        dp[0] = 0;

        int max = 0;
        for (int[] obj : objects) {

            int weight = obj[0];
            int value = obj[1];

            for (int j = M; j >= 0; --j) {
                int next = weight + j;

                if (next <= M) {
                    dp[next] = Math.max(dp[next], dp[j] + value);
                }
            }
        }

        for (int i : dp) {
            max = Math.max(max, i);
        }
        System.out.println(max);


    }
}
