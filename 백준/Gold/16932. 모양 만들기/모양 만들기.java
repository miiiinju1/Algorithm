
import java.io.DataInputStream;
import java.io.IOException;
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
        Reader reader = new Reader();


         N = reader.nextInt();
         M = reader.nextInt();

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = reader.nextInt();
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
                if (map[i][j] == 1 && !visited[i][j]) {
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
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din != null) {
                din.close();
            }
        }
    }

}
