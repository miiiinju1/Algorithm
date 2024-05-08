import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;

    static final int FALL = 10000;
    static int H, W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         H = Integer.parseInt(st.nextToken());
         W = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        Deque<Point> q = new ArrayDeque<>();
        for(int i= 0;i<H;i++) {
            final char[] input = br.readLine().toCharArray();
            for(int j = 0;j<W;j++) {
                if(input[j]=='.') {
                    map[i][j] = FALL;
                    q.add(new Point(i, j, 0));
                }
                else {
                    map[i][j] = Integer.parseInt(String.valueOf(input[j]));
                }
            }
        }

        int count = 0;

        boolean[][][] visited = new boolean[H][W][8];

        while(!q.isEmpty()) {
            final Point now = q.poll();

            count = Math.max(count, now.depth);

            for (int d = 0; d < 8; d++) {
                int y = dy[d] + now.y;
                int x = dx[d] + now.x;

                if (isValid(y, x)) {
                    if (map[y][x] != FALL && !visited[now.y][now.x][d]) {
                        map[y][x]-=1;
                        visited[now.y][now.x][d] = true;

                        if(map[y][x]<=0) {
                            map[y][x] = FALL;
                            q.add(new Point(y, x, now.depth + 1));
                        }
                    }
                }
            }
        }

        System.out.println(count);

    }
    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < H && x < W;
    }
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};


    static class Point {
        int y, x;
        int depth;



        public Point(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
    }
}
