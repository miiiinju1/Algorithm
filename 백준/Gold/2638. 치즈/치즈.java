import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Point {
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
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,-1,0,1};
    static int nowBackground = -2;
    static void bfs() {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        while (!q.isEmpty()) {
            Point now = q.poll();
//            System.out.println("now = " + now);
            if(visited[now.y][now.x])
                continue;
            if(map[now.y][now.x]>=0) {
                map[now.y][now.x]++;
                continue;
            }
            else {
                visited[now.y][now.x] = true;
            }

            for(int i= 0;i<4;i++) {

                int y = now.y+dy[i];
                int x = now.x+dx[i];

                if (y >= 0 && x >= 0 && y < N && x < M && !visited[y][x]) {
                    q.add(new Point(y, x));
                }

            }

        }


    }
    static int N,M;
    static int[][] map = new int[N][M];

    static boolean isCleared() {
        boolean flag = false;
        for(int i= 0;i<N;i++) {
            for(int j = 0;j<M;j++) {
                if(map[i][j]>=0) {
                    if(map[i][j]>=2) {
                        map[i][j] = nowBackground;
                    }
                    else {
                        map[i][j] = 0;
                        if(!flag) {
                            flag = true;
                        }
                    }
                }
            }
        }
        nowBackground--;
        return flag;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i =0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 0;j<M;j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp==0) temp = -1;
                else temp = 0;
                map[i][j] = temp;
            }
        }
        int time = 0;
        while(true) {
            bfs();
            time++;
//            for(int i= 0;i<N;i++) {
//                System.out.println();
//                for(int j = 0;j<M;j++) {
//                    System.out.print(map[i][j]+" ");
//                }
//            }
//            System.out.println();
            if(!isCleared()) {
                System.out.println(time);
                return ;
            }


        }


    }
}
