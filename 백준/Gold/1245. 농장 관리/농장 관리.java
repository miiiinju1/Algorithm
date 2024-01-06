import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {-1,-1,-1,0,0,1,1,1};
    static int[] dx = {-1,0,1,-1,1,-1,0,1};
    static int N,M;

    static class Point{
        int y,x;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append('}');
            return sb.toString();
        }

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static ArrayDeque<Point> q;
    static boolean bfs(int value) {
        while(!q.isEmpty()) {
            Point now = q.poll();

            for(int i = 0;i<8;i++) {
                int Y = dy[i] + now.y;
                int X = dx[i] + now.x;

                if(Y>=0&&X>=0&&Y<N&&X<M) {
                    if ((map[Y][X] == value || map[Y][X] < value)) {

                    }

                    else {
                        return false;
                    }
                }
            }
        }
        return true;

    }
    static int count = 0;
    static void search(int y,int x, int value) {
        q = new ArrayDeque<>();
        searchDetail(y, x, value);
        if(bfs(value)) {
            count++;
        }
    }
    static void searchDetail(int y,int x, int value) {
        q.add(new Point(y, x));
        visited[y][x] = true;
        for(int i= 0;i<8;i++) {
            int Y = y + dy[i];
            int X = x + dx[i];
            if (Y >= 0 && X >= 0 && Y < N && X < M && map[Y][X]==value&&!visited[Y][X]) {
                searchDetail(Y,X,value);
            }
        }

    }
    static boolean[][] cantGo;

    static boolean[][] visited ;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        cantGo = new boolean[N][M];

        for(int i =0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i =0;i<N;i++) {
            for(int j = 0;j<M;j++) {
                if(!visited[i][j]) {
                    search(i, j, map[i][j]);
                }
            }
        }

        System.out.println(count);



    }
}
