import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

//벽 부수고 이동하기 4
public class Main {
    static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int N,M;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static char[][] map = new char[N][M];
    static int[][] value = new int[N][M];
    static boolean[][] visited ;
    static int[][] 비교;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        map = new char[N][M];
        value = new int[N][M];
        
        비교 = new int[N][M];
        
        visited = new boolean[N][M];
        ArrayList<Point> walls = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '1') {
                    walls.add(new Point(i, j));
                }
            }
        }
        int 구별용 = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    if(!visited[i][j])
                    search(i, j, 구별용++);
                }
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(value[i][j] + " ");
//            }
//            System.out.println();
//        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == '1') {

                    HashSet<Integer> 중복제거 = new HashSet<>();
                    int max = 1;
                    for (int d = 0; d < 4; d++) {
                        int y = dy[d] + i;
                        int x = dx[d] + j;

                        if (y >= 0 & x >= 0 && y < N && x < M&& !중복제거.contains(비교[y][x])) {
                            max += value[y][x];
                            중복제거.add(비교[y][x]);
                        }

                    }
                    max %= 10;
                    sb.append(max);

                }
                else {
                    sb.append("0");
                }

            }
            sb.append("\n");



        }
        System.out.println(sb);
    }
        private static void search (int i, int j, int 구별){
            int count = 0;
            ArrayDeque<Point> q = new ArrayDeque<>();
            ArrayDeque<Point> result = new ArrayDeque<>();
            q.add(new Point(i, j));
            result.add(new Point(i, j));
            visited[i][j] = true;

            while (!q.isEmpty()) {
                Point now = q.poll();
                count++;
                for (int d = 0; d < 4; d++) {
                    int y = dy[d] + now.y;
                    int x = dx[d] + now.x;

                    if (y >= 0 & x >= 0 && y < N && x < M && !visited[y][x] && map[y][x] == '0') {
                        visited[y][x] = true;
                        q.add(new Point(y, x));
                        result.add(new Point(y, x));
                    }
                }
            }
            for (Point point : result) {
                value[point.y][point.x] = count;
                비교[point.y][point.x] = 구별;
            }
//        return count;
        }

}
