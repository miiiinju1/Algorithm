
import java.io.DataInputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {

    static class Point implements Comparable<Point> {
        int y, x, h;

        public Point(int y, int x, int h) {
            this.y = y;
            this.x = x;
            this.h = h;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.h, o.h);
        }
    }
    static int N, M;
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        N = reader.nextInt();
        M = reader.nextInt();

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                map[i][j] = reader.nextInt();

                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    visited[i][j] = true;
                    pq.add(new Point(i, j, map[i][j]));
                }
            }
        }

        int sum = 0;

        while(!pq.isEmpty()) {
            Point now = pq.poll();

            for (int d = 0; d < 4; ++d) {
                int Y = dy[d] + now.y;
                int X = dx[d] + now.x;

                if (isValid(Y, X) && !visited[Y][X]) {
                    visited[Y][X] = true;

                    sum += Math.max(0, now.h - map[Y][X]);

                    pq.add(new Point(Y, X, Math.max(map[Y][X], now.h)));
                }
            }

        }

        System.out.println(sum);

    }
    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

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
