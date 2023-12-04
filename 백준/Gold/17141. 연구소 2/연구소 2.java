import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int y,x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
     
    }
    static int N,M;
    static int[][] tempMap;
    static ArrayList<Point> virusMap;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int execute() {
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tempMap[i], 0, map[i], 0, N);
        }
        Queue<Point> q = new LinkedList<>();
        for (Point point : virus) {
            map[point.y][point.x] = 0;
            q.add(point);
        }
        int min = 0;
        while (!q.isEmpty()) {
            Point now = q.poll();

            for(int i = 0;i<4;i++) {
                int y = now.y + dy[i];
                int x = now.x + dx[i];

                if(y>=0&&x>=0&&y<N&&x<N&&map[y][x]!=-1&&(map[y][x]>map[now.y][now.x]+1)) {//
                    map[y][x] = map[now.y][now.x]+1;
                    q.add(new Point(y, x));
                }

            }

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]>min)
                    min = map[i][j];
            }
        }
        return min;



    }
    static boolean[] visited;
    static Point[] virus;
    static int max =Integer.MAX_VALUE;

static void combination(int index, int startIndex) {
    if (index == M) {
        int result = execute();
        max = Math.min(max, result);
        return;
    }
    for (int i = startIndex; i < virusMap.size(); i++) {
        virus[index] = virusMap.get(i);
        combination(index + 1, i + 1); // 중복된 조합을 생성하지 않도록 startIndex를 이용
    }
}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tempMap = new int[N][N];
        virusMap = new ArrayList<>();
        virus = new Point[M];
        visited = new boolean[M];
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            for(int j= 0;j<N;j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp==1) {
                    temp=-1;
                }
                else if(temp==2) {
                    temp = Integer.MAX_VALUE;
                    virusMap.add(new Point(i, j));
                }
                else {
                    temp = Integer.MAX_VALUE;
                }

                tempMap[i][j] = temp;

            }

        }
        combination(0,0);
        if(max==Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(max);

    }
}
