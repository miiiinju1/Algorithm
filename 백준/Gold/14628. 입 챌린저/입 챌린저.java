import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int POWER_INDEX = 0;
    static final int MANA_INDEX = 1;
    static int N,M,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());


        int[] sum =  new int[M+1];
        Arrays.fill(sum, Integer.MAX_VALUE);
        int[][] dp = new int[M+1][N];


        int[][] attack = new int[N][2];
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            attack[i][POWER_INDEX] = Y;
            attack[i][MANA_INDEX] = X;
            if (Y <= M) {
                dp[Y][i] = 1;
                sum[Y] = X;
            }
        }

        // 현재 공격까지
        for (int i = 0; i <= M; i++) {
            // N번째 공격을 써서
            if (sum[i] == Integer.MAX_VALUE) continue;


            for (int atk = 0; atk < N; atk++) {
                if (i + attack[atk][POWER_INDEX] > M) continue;

                int temp = 0;
                for (int j = 0; j < N; j++) {
                    int tempCount = atk == j ? dp[i][j] + 1 : dp[i][j];
                    if (tempCount != 0) {
                        temp += getGrade(tempCount, attack[j][MANA_INDEX]);
                    }
                }

                if (sum[i + attack[atk][POWER_INDEX]] > temp) {
                    sum[i + attack[atk][POWER_INDEX]] = temp;

                    // dp배열 초기화
                    for (int j = 0; j < N; j++) {
                        dp[i + attack[atk][POWER_INDEX]][j] = atk == j ? dp[i][j] + 1 : dp[i][j];
                    }

                }

            }
        }
        System.out.println(sum[M]);

    }
    static int getGrade(int count, int origin) {
        int now = 0;

        for(int i = 0; i < count; i++) {
            now += (i * K+ origin);
        }
        return now;
    }
}
