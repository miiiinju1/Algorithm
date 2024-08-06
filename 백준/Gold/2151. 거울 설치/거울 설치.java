
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    static class Point {
        int y, x, mirrorCount;

        int beforeD;

        public Point(int y, int x, int mirrorCount, int beforeD) {
            this.y = y;
            this.x = x;
            this.mirrorCount = mirrorCount;
            this.beforeD = beforeD;
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N;
    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];

        int startY = -1, startX = -1, endY = -1, endX = -1;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '#') {
                    if(startY==-1) {
                        startY = i;
                        startX = j;
                    }
                    else {
                        endY = i;
                        endX = j;
                    }
                }
            }
        }

        int[][][] visited = new int[N][N][4];

        Deque<Point> q = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            int y = startY + dy[d];
            int x = startX + dx[d];
            if (isValid(y, x) && map[y][x] != '*') {
                visited[y][x][0] = 0;
                q.add(new Point(y, x, 0, d));
            }

        }
        for(int i= 0;i<N;i++) {
            for(int j=0;j<N;j++) {
                for(int d = 0;d<4;d++) {
                    visited[i][j][d] = Integer.MAX_VALUE;
                }
            }
        }
        for(int i= 0;i<4;i++) {
            visited[startY][startX][i] = 0;
        }

        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Point now = q.poll();

            if (now.y == endY && now.x == endX) {
                min = Math.min(min, now.mirrorCount);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int Y = now.y + dy[d];
                int X = now.x + dx[d];
                int mirrorConut = now.mirrorCount;
                if(!isValid(Y,X)) continue;
                if(d%2 != now.beforeD%2) {
                    if(map[now.y][now.x]=='!' && mirrorConut<N*N) {
//                        System.out.println("now.y+\" \"+now.x = " + now.y + " " + now.x);
//                        System.out.println("map[now.y][now.x] = " + map[now.y][now.x]);
                        mirrorConut++;
                    }
                    else {
                        continue;
                    }
                }
                if (map[Y][X] != '*') {
                    if (visited[Y][X][d] > mirrorConut) {
//                        System.out.println("Y+\" \"+X+\" \"+mirrorConut = " + Y+" "+X+" "+mirrorConut);
                        visited[Y][X][d] = mirrorConut;
                        q.add(new Point(Y, X, mirrorConut, d));
                    }

                }


            }


        }
        System.out.println(min);





    }
}
