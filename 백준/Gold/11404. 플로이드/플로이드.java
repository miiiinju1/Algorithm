import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = Integer.MAX_VALUE/2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());


        int[][] map = new int[N + 1][N + 1];
        for(int i= 1;i<=N;i++) {
            Arrays.fill(map[i],MAX);
            map[i][i] = 0;

        }
        for(int i = 0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(map[start][end]>weight)
             map[start][end] = weight;
        }

        for(int k = 1;k<=N;k++) {
            for (int i = 1; i <= N; i++) {
                if (map[i][k] != MAX) {
                    for (int j = 1; j <= N; j++) {
                        map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j]==MAX) {
                    bw.write("0 ");
                }
                else
                 bw.write(map[i][j]+" ");
            }
            bw.write("\n");

        }
        bw.flush();bw.close();


    }

}
