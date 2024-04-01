import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int[][] map;
    static int[][] valueMap;
    static boolean[][] visited;
    static int N,M;
    static int dfs(int Y, int X) {
        int v = map[Y][X];
        int temp = 0;
//        System.out.println(Y+" "+X+" "+count);

        if(valueMap[Y][X]!=-1)
            return valueMap[Y][X];
        for (int d = 0; d < 4; d++) {
            int y = (v*dy[d])+Y;
            int x = (v*dx[d])+X;

            if(y>=0&&x>=0&&y<N&&x<M&&map[y][x]!=-1) {
                if(!visited[y][x]) {
                    visited[y][x] = true;
                    int dfs  = dfs(y,x);
                    if(dfs==-1) {
                        return -1;
                    }
                    visited[y][x] = false;
                    temp = Math.max(temp, dfs+1);
                }
                else {
                    return -1;
                }
            }
            else {
                temp = Math.max(temp,1);
            }
        }

//        System.out.println("temp = " + temp);
        valueMap[Y][X] = Math.max(valueMap[Y][X], temp);
        return temp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         map = new int[N][M];
         valueMap = new int[N][M];
        visited = new boolean[N][M];
        for(int i =0;i<N;i++) {
            final char[] input = br.readLine().toCharArray();
            Arrays.fill(valueMap[i],-1);
            for(int j= 0;j<M;j++) {
                if(input[j]=='H') {
                    map[i][j] = -1;
                }
                else {
                    map[i][j] = input[j]-'0';
                }
            }
        }

        System.out.println(dfs(0, 0));

    }
}
