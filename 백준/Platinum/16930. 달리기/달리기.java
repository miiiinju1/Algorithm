import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }

        int y, x, count;
        int d;
        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }

        public Point(int y, int x, int count, int d) {
            this.y = y;
            this.x = x;
            this.count = count;
            this.d = d;
        }
    }

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        int[][] visited = new int[N][M];


        for(int i= 0;i<N;i++) {
            final String str = br.readLine();
            map[i] = str.toCharArray();
            Arrays.fill(visited[i], Integer.MAX_VALUE);


        }
        st = new StringTokenizer(br.readLine());


        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;
        int destY = Integer.parseInt(st.nextToken()) - 1;
        int destX = Integer.parseInt(st.nextToken()) - 1;

        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(startY, startX, 0));

        while(!q.isEmpty()) {
            final Point now = q.poll();

            if (now.y == destY && now.x == destX) {
                System.out.println(now.count);
                return;
            }

            for(int d = 0;d<4;d++) {

                int k = 1;

                for (; k <=K ; k++) {
                    int y = dy[d] * k + now.y;
                    int x = dx[d] * k + now.x;

                    if (isValid(y, x) && map[y][x] == '.' && visited[y][x] >= now.count + 1) {
                        if(visited[y][x]==Integer.MAX_VALUE)
                            q.add(new Point(y, x, now.count + 1));
                        visited[y][x] = now.count + 1;
                    }
                    else {
                        break;
                    }
                }
            }
        }

        System.out.println(-1);

    }
    private static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

}
