
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

        int[][] sum = new int[n+1][m+1];
        int[][] dp = new int[n][m];
        int max = 0;
        for(int i = 0;i<n;i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                sum[i+1][j+1] = str.charAt(j) - '0';
                if(sum[i+1][j+1] == 1) {
                    dp[i][j] = 1;
                    max = 1;
                }

            }
        }
        for(int i= 0;i<=n;i++) {
            for(int j= 1;j<=m;j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }
        for(int j = 0;j<=m;j++) {
            for(int i =1;i<=n;i++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

//        for(int i= 1;i<=n;i++) {
//            for(int j= 1;j<=m;j++) {
//                System.out.print(sum[i][j]+" ");
//            }
//            System.out.println();
//        }


        for(int i = 1;i<n;i++) {
            for(int j = 1;j<m;j++) {
//                System.out.println("i + \" \" + j = " + i + " " + j);
                if (dp[i][j]== 1 && dp[i - 1][j - 1] != 0) {
                    int len = dp[i - 1][j - 1];

                    for(;len>0;len--) {
//                        System.out.println("len = " + len);
//                        System.out.println("sum[i][j+1] = " + sum[i][j + 1]);
//                        System.out.println("sum[i-len][j+1] = " + sum[i - len][j + 1]);
//                        System.out.println("sum[i][j] = " + sum[i][j]);
//
//                        System.out.println("sum[i - len][j] = " + sum[i - len][j]);
                        int heightTest = sum[i][j + 1] - sum[i - len][j + 1] - sum[i][j] + sum[i - len][j];
//                        System.out.println("heightTest = " + heightTest);
                        if (heightTest != len) {
                            continue;
                        }
                        int widthTest = sum[i + 1][j] - sum[i][j] - sum[i + 1][j - len] + sum[i][j - len];
//                        System.out.println("widthTest = " + widthTest);
                        if (widthTest != len) {
                            continue;
                        }
                        dp[i][j] = Math.max(dp[i][j], len + 1);
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
        }

//        for(int i= 0;i<n;i++) {
//            for(int j= 0;j<m;j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }
        System.out.println(max*max);
    }
}

//4 4
//0100
//0111
//1111
//1110
//4 4
//1111
//0111
//1110
//0010