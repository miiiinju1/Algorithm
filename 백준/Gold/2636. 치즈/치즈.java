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
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N, M;

    static void erase(int val) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == -100) {
                    map[i][j] = val;
                }
            }
        }
    }

    static void bfsAir(int startY, int startX,int val) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(startY, startX));
        while (!q.isEmpty()) {
            Point now = q.poll();


            for (int i = 0; i < 4; i++) {
                int y = dy[i] + now.y;
                int x = dx[i] + now.x;
                if (y >= 0 && x >= 0 && y < N && x < M&&map[y][x]!=-100 &&map[y][x] !=val) {
                    if (map[y][x] == -1) {
                        map[y][x] = -100;
                        count++;
                    } else {
                        map[y][x]=val;
                        q.add(new Point(y, x));
                    }

                }

            }
        }


    }



    static int count = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp==1) {
                    temp = -1;
                }
                map[i][j] = temp;
            }
        }

        int sum = 1;
        while (count > 0) {
            int temp = count;
            count = 0;
            bfsAir(0,0,sum);

            if (count == 0) {
                System.out.println(sum-1);
                System.out.println(temp);
                return;
            }
            erase(sum);
            sum++;

        }

    }
}
