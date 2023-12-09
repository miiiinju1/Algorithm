import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] map = new boolean[2001][2001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        for (int z = 0; z < N; z++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken())*2+1000;
            int y1 = Integer.parseInt(st.nextToken())*2+1000;
            int x2 = Integer.parseInt(st.nextToken())*2+1000;
            int y2 = Integer.parseInt(st.nextToken())*2+1000;

            for (int i = y1; i <= y2; i++) {
                if (i == y1 || i == y2) {
                    for (int j = x1; j <= x2; j++) {
                        if(!map[i][j]) {
                            map[i][j] = true;
                            count++;
                        }
                    }
                }
                else {
                    if(!map[i][x1]) {
                        map[i][x1] = true;
                        count++;
                    }

                    if(!map[i][x2]) {
                        map[i][x2] = true;
                        count++;
                    }

                }
            }
        }
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(1000,1000));
        long tempCount=0;
        if(map[1000][1000]) {
            while (!q.isEmpty()) {
                Point now = q.poll();

                if(!map[now.y][now.x]) {
                    continue;
                }
                map[now.y][now.x] = false;
                tempCount++;

                for(int i= 0;i<4;i++) {
                    int y = now.y + dy[i];
                    int x = now.x + dx[i];

                    if (y >= 0 && x >= 0 && y < 2001 && x < 2001 && map[y][x]) {
                        q.add(new Point(y, x));

                    }
                }
            }
        }
        if(tempCount==count) {
            System.out.println(0);
            return ;
        }
         long sum = 0;
        for(int i=0;i<2001;i++) {
            for(int j= 0;j<2001;j++) {

                if(map[i][j]) {
                    sum++;
                    q.add(new Point(i,j));

                    while(!q.isEmpty()) {
                        Point now = q.poll();

                        if(!map[now.y][now.x]) {
                            continue;
                        }
                        map[now.y][now.x] = false;
                        tempCount++;
                        for(int d= 0;d<4;d++) {
                            int y = now.y+dy[d];
                            int x = now.x+dx[d];

                            if (y >= 0 && x >= 0 && y < 2001 && x < 2001 && map[y][x]) {
                                q.add(new Point(y, x));

                            }
                        }
                    }
                    if(tempCount==count) {
                        System.out.println(sum);
                    }

                }

            }
        }


    }
}