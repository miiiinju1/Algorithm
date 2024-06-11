
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int now, gas;
        public Edge(int now, int gas) {
            this.now = now;
            this.gas = gas;
        }
    }
    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[][] map;
    static int distance(Point p1, Point p2) {
        return (int)Math.ceil(Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2))/10);
    }
    static int n,k;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
         k = Integer.parseInt(st.nextToken());

        map = new int[n + 2][n + 2];
        Point[] points = new Point[n+2];
        points[0] = new Point(0, 0);
        points[n + 1] = new Point(10000, 10000);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Point(y, x);

        }
//        System.out.println("distance(new Point(0,0), new Point(1000,10)) = " + distance(new Point(2,1), new Point(37,43)));

        for (int i = 0; i <= n+1; i++) {
            for (int j = i + 1; j <= n+1; j++) {
                //i랑j거리
                map[i][j] = distance(points[i], points[j]);
                map[j][i] = map[i][j];
            }
        }

//        for(int i= 0;i<=n+1;i++) {
//            for(int j= 0;j<=n+1;j++) {
//                System.out.printf("%06d ",map[i][j]);
//
//            }
//            System.out.println();
//        }


        int lo = 0, hi = 15000;

        while(lo+1<hi) {

            int mid = (hi - lo) / 2 + lo;

            if(check(mid)) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        System.out.println(hi);

    }

    static class Phase {
        int now;

        public Phase(int now, int depth) {
            this.now = now;
            this.depth = depth;
        }

        int depth;

    }

    private static boolean check(int mid) {
//        System.out.println("mid = " + mid);

        boolean[] visited = new boolean[n + 2];
        Deque<Phase> q = new ArrayDeque<>();
        q.add(new Phase(0, 0));
        visited[0] = true;

        while(!q.isEmpty()) {
            final Phase now = q.poll();

            if(now.now==n+1) {
                return true;
            }
            for (int i = 1; i <= n+1; i++) {
                if (map[now.now][i] <= mid &&
                        !visited[i]) {
                    if(now.depth <= k) {
                        if(i == n+1) {
                            return true;
                        }
                        q.add(new Phase(i, now.depth + 1));
                        visited[i] = true;
                    }
                }
            }

        }

        return false;





    }
}
