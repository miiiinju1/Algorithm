
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static void search(int startY, int startX, int endY, int endX) throws IOException {

        int[] sum = {0,0,0};
        //J = 0
        for(int z = 0;z<3;z++) {
            sum[z]+=map[z][endY][endX];

            if(startX-1>=0&&startY-1>=0) {
                sum[z]+= map[z][startY-1][startX-1];
            }
            if(startX-1>=0) {
                sum[z]-= map[z][endY][startX-1];
            }
            if(startY-1>=0) {
                sum[z]-= map[z][startY-1][endX];
            }
        }
        sb.append(sum[0]).append(" ");
        sb.append(sum[1]).append(" ");
        sb.append(sum[2]).append("\n");

//        sb.append(String.format("%d %d %d\n", sum[0], sum[1], sum[2]));
    }

    static int[][][] map;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        map = new int[3][M][N];
        //0 = J
        //1 = O
        //2 = I
        for(int i= 0;i<M;i++) {
            String temp = br.readLine();
            for(int j= 0;j<N;j++) {
                switch(temp.charAt(j)) {
                    case 'J':
                        map[0][i][j] = 1;
                        break;
                    case 'O':
                        map[1][i][j] = 1;
                        break;
                    case 'I':
                        map[2][i][j] = 1;
                        break;
                }
            }

        }

        //O(1000*1000*3*2)
        for(int z = 0;z<3;z++) {
            for(int i= 0;i<M;i++) {
                for(int j= 1;j<N;j++) {
                    map[z][i][j] += map[z][i][j-1];
                }
            }

        }
        for(int z = 0;z<3;z++) {
            for(int j= 0;j<N;j++) {
                for(int i= 1;i<M;i++) {
                    map[z][i][j] += map[z][i-1][j];
                }
            }
        }

        for(int i= 0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken())-1;
            int startX = Integer.parseInt(st.nextToken())-1;
            int endY = Integer.parseInt(st.nextToken())-1;
            int endX = Integer.parseInt(st.nextToken())-1;

            search(startY, startX, endY, endX);

        }
        bw.write(sb.toString());
        bw.flush();bw.close();



    }
}
