import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        for(int i= 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0;j<N;j++) {
                if (!st.nextToken().equals("1")) {
                    map[i][j]=true;
                }
            }

        }
        int[][][] dp = new int[3][N][N];
        dp[0][0][1] = 1; //가로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                    for(int direction = 0;direction<3;direction++) {
                        if(dp[direction][i][j]>0&&map[i][j]) {
                            //가로


                            if(direction==0) {
                                //그대로
                                if (j + 1 < N && map[i][j + 1]) {
                                    dp[0][i][j + 1] += dp[0][i][j];
                                }
                                //대각선
                                if(i+1<N&&j+1<N&&map[i][j+1]&&map[i+1][j]&&map[i][j+1]&&map[i+1][j+1]) {
                                    dp[1][i+1][j+1] += dp[0][i][j];
                                }
                            }

                            if(direction == 2) {
                                //그대로
                                if (i + 1 < N && map[i + 1][j]) {
                                    dp[2][i + 1][j] += dp[2][i][j];
                                }
                                //대각선
                                if(i+1<N&&j+1<N&&map[i][j+1]&&map[i+1][j]&&map[i][j+1]&&map[i+1][j+1]) {
                                    dp[1][i+1][j+1] += dp[2][i][j];
                                }

                            }
                            if(direction == 1) {
                                //오른쪽으로
                                if (j + 1 < N && map[i][j + 1]) {
                                    dp[0][i][j + 1] += dp[1][i][j];
                                }
                                //왼쪽으로
                                if (i + 1 < N && map[i + 1][j]) {
                                    dp[2][i + 1][j] += dp[1][i][j];
                                }
                                //그대로
                                if(i+1<N&&j+1<N&&map[i][j+1]&&map[i+1][j]&&map[i][j+1]&&map[i+1][j+1]) {
                                    dp[1][i+1][j+1] += dp[1][i][j];
                                }
                            }
                        }
                    }
            }
        }

        System.out.println(dp[0][N-1][N-1]+dp[1][N-1][N-1]+dp[2][N-1][N-1]);


    }
}

