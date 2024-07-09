import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x, y, dist;

    Point(int y, int x, int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }
}

public class Main {
    static int n, m;
    static int f_dst, s_dst;
    static Point[][] map;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Point[] p = new Point[4];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            p[i] = new Point(y, x, 0);
        }

        int ans1 = bfs(p[0], p[1], p[2], p[3]);
        int ans2 = bfs(p[2], p[3], p[0], p[1]);
        
        if (ans1 == Integer.MAX_VALUE && ans2 == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(Math.min(ans1, ans2));
    }

    private static int bfs(Point a1, Point a2, Point b1, Point b2) {
        f_dst = 0;
        s_dst = 0;
        map = new Point[101][101];
        boolean[][] visit = new boolean[101][101];
        Queue<Point> q = new LinkedList<>();
        q.add(a1);
        visit[a1.y][a1.x] = true;
        visit[b1.y][b1.x] = true;
        visit[b2.y][b2.x] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            if (now.x == a2.x && now.y == a2.y) {
                f_dst = now.dist;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                int dist = now.dist + 1;
                if (nx < 0 || ny < 0 || nx > n || ny > m) continue;
                if (visit[ny][nx]) continue;
                q.add(new Point(ny, nx, dist));
                visit[ny][nx] = true;
                map[ny][nx] = now;
            }
        }

        visit = new boolean[101][101];
        Point now = new Point(a2.y, a2.x,0);
        visit[now.y][now.x] = true;
        while (true) {
            visit[now.y][now.x] = true;
            if (now.x == a1.x && now.y == a1.y) {
                break;
            }
            now = map[now.y][now.x];
        }

        q = new LinkedList<>();
        q.add(b1);
        visit[b1.y][b1.x] = true;

        while (!q.isEmpty()) {
            now = q.poll();
            if (now.x == b2.x && now.y == b2.y) {
                s_dst = now.dist;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                int dist = now.dist + 1;
                if (nx < 0 || ny < 0 || nx > n || ny > m) continue;
                if (visit[ny][nx]) continue;
                q.add(new Point(ny, nx, dist));
                visit[ny][nx] = true;
                map[ny][nx] = now;
            }
        }
        if (s_dst == 0) return Integer.MAX_VALUE;
        else return f_dst + s_dst;
    }
}
