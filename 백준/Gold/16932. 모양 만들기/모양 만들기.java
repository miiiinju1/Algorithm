
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int num, size;

        public Node(int num, int size) {
            this.num = num;
            this.size = size;
        }
    }
    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0, -1, 0, 1};
    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }
    static int N,M;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        Node[][] result = new Node[N][M];
        int num = 1;

        int max = 0;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                int size = 0;
                List<Point> temp = new ArrayList<>();
                if (!visited[i][j] && map[i][j] == 1) {
                    Deque<Point> q = new ArrayDeque<>();
                    q.add(new Point(i, j));
                    visited[i][j] = true;

                    while (!q.isEmpty()) {
                        final Point now = q.poll();
                        ++size;

                        temp.add(now);

                        for (int d = 0; d < 4; d++) {
                            int y = dy[d] + now.y;
                            int x = dx[d] + now.x;
                            if (isValid(y, x) && !visited[y][x] && map[y][x] == 1) {
                                visited[y][x] = true;
                                q.add(new Point(y, x));
                            }
                        }
                    }

                    for (Point point : temp) {
                        result[point.y][point.x] = new Node(num, size);
                    }
                    max = Math.max(max, size);
                    ++num;
                }

            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(result[i][j]==null) {

                    Map<Integer, Integer> count = new HashMap<>();
                    for (int d = 0; d < 4; d++) {
                        int y = dy[d] + i;
                        int x = dx[d] + j;

                        if(isValid(y,x) && result[y][x] != null) {
                            count.put(result[y][x].num, result[y][x].size);
                        }

                    }
                    final int sum = count.values().stream()
                            .mapToInt(Integer::intValue)
                            .sum() + 1;
                    max = Math.max(max, sum);

                }
            }
        }
        System.out.println(max);




    }

}
