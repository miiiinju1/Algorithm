
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][m + 1];

        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }
        for(int i = 1;i<=n;i++) {
            dp[i][0] = i;
        }

        String from = br.readLine();
        String to = br.readLine();

        char[] fromAry = new char[from.length() + 1];
        for(int i = 0;i<from.length();i++) {
            fromAry[i + 1] = from.charAt(i);
        }

        char[] toAry = new char[to.length() + 1];
        for(int i = 0;i<to.length();i++) {
            toAry[i + 1] = to.charAt(i);
        }


        for(int i = 1;i<=n;i++) {
            for(int j= 1;j<=m;j++) {
                if(fromAry[i]==toAry[j]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if(fromAry[i] == 'i' && (toAry[j] == 'j' || toAry[j] == 'l') ) {
                    dp[i][j] = dp[i - 1][j - 1];

                }
                else if (fromAry[i] == 'v' && toAry[j] == 'w') {
                    dp[i][j] = dp[i - 1][j - 1];

                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        System.out.println(dp[n][m]);

    }
}
