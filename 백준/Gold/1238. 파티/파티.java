import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        for(int i= 1;i<=N;i++) {
            Arrays.fill(map[i],Integer.MAX_VALUE/2);
        }

        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[start][end] = weight;
        }
        for(int i = 1;i<=N;i++) {
            map[i][i] = 0;
        }
        int max = 0;
        for(int k = 1;k<=N;k++) {
            


                for (int i = 1; i <= N; i++) {
                    if (map[i][k] != Integer.MAX_VALUE/2) {
                    for (int j = 1; j <= N; j++) {
                        map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);

                    }
                }
            }
        }

        for(int i= 1;i<=N;i++) {
            if (map[i][X] != Integer.MAX_VALUE / 2) {
                max = Math.max(max, map[i][X]+map[X][i]);
            }
        }
        System.out.println(max);






    }

}

