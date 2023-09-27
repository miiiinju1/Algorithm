
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N;
    static boolean dfs(int y, int x)
    {
        visited[y][x] = true;
        if(y==N-1 && x==N-1)
            return true;

        int jump_y = y+map[y][x];
        if(jump_y>=0&&jump_y<N&&!visited[jump_y][x])
            if(dfs(jump_y,x))
                return true;


        int jump_x = x+map[y][x];
        if(jump_x>=0&&jump_x<N&&!visited[y][jump_x])
            if(dfs(y,jump_x))
                return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        if (dfs(0, 0)) {
            System.out.println("HaruHaru");
            return;
        }
            System.out.println("Hing");

    }
}
