
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] ary = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ary[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                ary[i][j] += ary[i][j - 1];
            }

        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ary[i][j] += ary[i-1][j];
            }

        }



        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            long sum= 0L;
                int y1 = Integer.parseInt(st.nextToken());
                int x1 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());

                sum+= ary[y2-1][x2-1];




            if(x1>1)
              sum-= ary[y2-1][x1-2];
            if(y1>1)
                sum-= ary[y1-2][x2-1];

            if(x1>1&&y1>1)
                sum+= ary[y1-2][x1-2];

            bw.write(sum + "\n");


        }
        bw.flush();
        bw.close();

    }
}
