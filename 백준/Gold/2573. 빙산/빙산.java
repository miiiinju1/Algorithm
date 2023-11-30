
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N,M;
    static class Point {
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append('}');
            return sb.toString();
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static void melt(int startY, int startX) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startY, startX));
        boolean[][] visited = new boolean[N][M];

        while (!q.isEmpty()) {
            Point point = q.poll();
            if(visited[point.y][point.x])
                continue;

            visited[point.y][point.x] = true;
            for(int i= 0;i<4;i++) {
                int y = dy[i]+ point.y;
                int x = dx[i]+ point.x;
                if(y>=0&&x>=0&&x<M&&y<N&&!visited[y][x]) {
                    if(map[y][x]==0) {
                        if(map[point.y][point.x]>0) {
                            map[point.y][point.x]--;
                        }
                    }
                    else {
                        q.add(new Point(y, x));
                    }

                }

            }


        }
    }
    static void melting() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]!=0) {
                    melt(i,j);
                    return ;
                }
            }
        }
    }

    static int search(int startY, int startX) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startY, startX));
        boolean[][] visited = new boolean[N][M];
        int count = 0;
        while (!q.isEmpty()) {
            Point point = q.poll();
            if(visited[point.y][point.x])
                continue;

            count++;
            visited[point.y][point.x] = true;
            for(int i= 0;i<4;i++) {
                int y = dy[i]+ point.y;
                int x = dx[i]+ point.x;
                if(y>=0&&x>=0&&x<M&&y<N&&!visited[y][x]&&map[y][x]>0) {
                    q.add(new Point(y, x));
                }
            }
        }
        return count;
    }
    static int countSum;
    static boolean isDivided() {
        countSum = 0;
        for(int i= 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
                if(map[i][j]>0)
                    countSum++;

            }
        }

        if(countSum==0)
            return false;

        for(int i= 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
                if(map[i][j]!=0) {
                    int result = search(i,j);
                    return result != countSum;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count =0;
        while(true) {
            melting();
            count++;
            if(isDivided()) {

                System.out.println(count);
                return ;
            }
            if(countSum==0) {
                System.out.println(0);
                return ;
            }

        }




    }
}
