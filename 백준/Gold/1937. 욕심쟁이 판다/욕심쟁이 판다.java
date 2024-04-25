
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] value;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static int result = 0;
    static boolean isValid(int y, int x) {
        return y>=0&&x>=0&&y<N&x<N;
    }
    static int search(int Y, int X) {

        if(value[Y][X]!=-1) {
            return value[Y][X];
        }
        int sum = 1;
        for(int d = 0;d<4;d++) {
            int y = dy[d]+Y;
            int x = dx[d]+X;

            if(isValid(y,x) && map[Y][X] < map[y][x]) {
                sum = Math.max(sum, 1 + search(y, x));
            }
        }
        value[Y][X] = sum;
        result = Math.max(sum, result);
        return sum;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        value = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(value[i], -1);
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i= 0;i<N;i++) {
            for(int j= 0;j<N;j++) {
                if(value[i][j]==-1) 
                    search(i, j);

            }
        }
        System.out.println(result);

    }


}
