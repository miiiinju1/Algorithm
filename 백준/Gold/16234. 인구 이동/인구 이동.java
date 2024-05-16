
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] visited;
    static int[][] map;
    static int N,L,R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         L = Integer.parseInt(st.nextToken());
         R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        int phase = 1;
        while(true) {

            List<List<Point>> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if(visited[i][j] != phase) {
                        visited[i][j] = phase;
                        List<Point> temp = search(i, j, phase);
                        if(temp!=null) {
                            list.add(temp);
                        }
                    }



                }

            }
            if(list.isEmpty()) {
                break;
            }
            phase ++;
//            System.out.println("list = " + list);
            integration(list);


        }
        System.out.println(phase-1);
        // 1. 전체 BFS 탐색하면서 공유할 것 끼리 묶기

        // 2. 다 묶은 뒤에, 묶은 좌표끼리 값 같게 만들기


    }

    private static void integration(List<List<Point>> list) {
        for (List<Point> points : list) {
            int sum = 0;
            for (Point point : points) {
                sum += map[point.y][point.x];
            }
            final int newValue = sum / points.size();
            for (Point point : points) {
                map[point.y][point.x] = newValue;
            }
        }
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static boolean isValid(int y, int x) {
        return y>=0&& x>=0&&y<N&&x<N;
    }

    static List<Point> search(int y, int x, int z) {

        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(y, x));

        List<Point> result = new ArrayList<>();
        while(!q.isEmpty()) {

            final Point now = q.poll();

            result.add(now);

            for(int d = 0;d<4;d++) {
                int Y = dy[d] + now.y;
                int X = dx[d] + now.x;


                if (isValid(Y, X) && visited[Y][X] != z && available(map[now.y][now.x], map[Y][X])) {
                    visited[Y][X] = z;
                    q.add(new Point(Y, X));
                }
            }


        }

        if(result.size()==1) {
            return  null;
        }
        return result;


    }

    private static boolean available(int i, int i1) {
        int diff = Math.abs(i - i1);

        return diff >= L && diff <= R;

    }
}
