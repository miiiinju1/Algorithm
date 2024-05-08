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

//        int[][][] visited = new int[H][W][8];
        boolean[][][] visited = new boolean[H][W][8];
//        for(int i= 0;i<H;i++)
//            Arrays.fill(visited[i][j], -1);

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
                            q.add(new Point(y, x, now.depth + 1));
                        }
                    }
                }
            }
        }

        System.out.println(count - 1);

//        while(true) {
//            for(int a = 0;a<H;a++) {
//                for(int b= 0;b<W;b++) {
//                    if(map[a][b]!=FALL) {
//
//                    }
//                }
//            }
//
//            Deque<Point> q = new ArrayDeque<>();
//            q.add(new Point(0, 0));
//            while(!q.isEmpty()) {
//                final Point now = q.poll();
//
//                for (int d = 0; d < 4; d++) {
//                    int y = dy4[d] + now.y;
//                    int x = dx4[d] + now.x;
//                    if (isValid(y, x)) {
//                        if (map[y][x] == FALL && visited[y][x] != count) {
//                            visited[y][x] = count;
//                            q.add(new Point(y, x));
//                        }
//                    }
//
//                }
//            }
//
//
//            if(map[a][b]!=FALL) {
//                int tempCount = 0;
//                for (int d = 0; d < 8; d++) {
//                    int y = dy[d] + a;
//                    int x = dx[d] + b;
//
//                    if (isValid(y, x)) {
//                        if (temp[y][x] == FALL) {
//                            tempCount++;
//                        }
//                    }
//                }
//                if (tempCount >= map[a][b]) {
//                    map[a][b] = FALL;
//                }
//            }


//                        }
//                    }


//            cutting(temp, map);

//            if(cutting(temp, map)) {
//                break;
//            }
//            count++;
//            map = temp;
////        }
//        System.out.println(count);

    }

    static boolean cutting(int[][] a, int[][] b) {
        boolean flag = true;
        for(int i= 0;i<H;i++) {
            for(int j= 0;j<W;j++) {
                if(a[i][j]<=0) {
                    a[i][j] = FALL;
                    flag = false;
                }
                else {
                    a[i][j] = b[i][j];
                }
            }
        }
        return flag;
    }
    static boolean isSame(int[][] a, int[][] b) {
        for(int i= 0;i<H;i++) {
            for(int j= 0;j<W;j++) {
                if(a[i][j] != b[i][j]) {
                    return  false;
                }
            }
        }
        return true;
    }
    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < H && x < W;
    }
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int[] dy4 = {-1, 0, 1, 0};
    static int[] dx4 = {0, -1, 0, 1};

    static class Point {
        int y, x;
        int depth;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Point(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
    }
    static private int[][] arrayCopy(int[][] from) {
        int[][] result = new int[from.length][from[0].length];

        for (int i = 0; i < from.length; i++) {

            for (int j = 0; j < from[0].length; j++) {
                result[i][j] = from[i][j];

            }
        }
        return result;
    }
    static private void printMap(int[][] ary) {
        for (int i = 0; i < ary.length; i++) {
            System.out.println();
            for (int j = 0; j < ary[0].length; j++) {
                System.out.printf("%5d ", ary[i][j]);
            }
        }
    }
}
