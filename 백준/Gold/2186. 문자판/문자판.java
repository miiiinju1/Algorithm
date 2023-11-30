
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K;
    static int[] dy;
    static int[] dx;
    static int[][][] valueMap;
    static String[][] map;
    static String[] str;

    static int dfs(int startY, int startX, int index) {

        if(index == str.length-1 ) {
            if(str[index].equals(map[startY][startX])) {
                valueMap[startY][startX][index] = 1;
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            if(!str[index].equals(map[startY][startX])) {
                return 0;
            } else {
                if(valueMap[startY][startX][index]!=-1) {
                    return valueMap[startY][startX][index];
                }
            }

        }
        int count = 0;
        for(int i= 0;i<4*K;i++) {
            int y = startY+dy[i];
            int x = startX+dx[i];
            if(y>=0&&x>=0&&y<N&&x<M) {
                count += dfs(y, x, index + 1);
            }

        }
        valueMap[startY][startX][index]=count;


        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] baseY = {-1, 0, 1, 0};
        int[] baseX = {0, 1, 0, -1};
        ArrayList<Integer> list_dy = new ArrayList<>();
        ArrayList<Integer> list_dx = new ArrayList<>();
        for(int i= 1;i<=K;i++) {
            for(int j=0;j<4;j++) {
                list_dy.add(baseY[j] * i);
                list_dx.add(baseX[j] * i);
            }
        }
        dy = list_dy.stream().mapToInt(i -> i).toArray();
        dx = list_dx.stream().mapToInt(i -> i).toArray();
        map = new String[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }

        str = br.readLine().split("");
        valueMap = new int[N][M][str.length];
        for(int i = 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
                for(int k = 0;k<str.length;k++) {
                    valueMap[i][j][k] = -1;
                }
            }
        }
        int sum = 0;

        for(int i= 0;i<N;i++){
            for (int j = 0; j < M; j++) {
                if (map[i][j].equals(str[0])) {
                    sum+=dfs(i,j,0);
                }
            }
        }


        System.out.println(sum);


    }
}
//4 4 1
//AAAA
//AAAA
//AAAA
//AAAA
//AAAAA


//4 4 2
//KAKT
//XEAS
//YRWU
//ZBQP
//BREAK


//4 3 1
//NDE
//KCD
//ABA
//BAO
//ABCDE