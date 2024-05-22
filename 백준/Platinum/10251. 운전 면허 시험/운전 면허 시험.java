
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = reader.nextInt();
        for (int tc = 0; tc < T; tc++) {

            int M = reader.nextInt();
            int N = reader.nextInt();
            int L = reader.nextInt();
            int G = reader.nextInt();

            int turn = Math.max(M, N);

            int[][][][] map = new int[M][N][turn * 2 + 1][2];

            int[][] widthCost = new int[M][N - 1];
            int[][] heightCost = new int[M - 1][N];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N - 1; j++) {
                    widthCost[i][j] = reader.nextInt();
                }
            }
            for (int i = 0; i < M - 1; i++) {
                for (int j = 0; j < N; j++) {
                    heightCost[i][j] = reader.nextInt();
                }
            }
            int INIT = 1000000000;  // 큰 값으로 초기화하여 오버플로 방지
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    for (int t = 0; t < turn * 2 + 1; t++) {
                        map[i][j][t][0] = INIT;
                        map[i][j][t][1] = INIT;
                    }
                }
            }
            map[0][0][0][0] = 0;
            map[0][0][0][1] = 0;

            // 0은 아래, 1은 오른쪽
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < M; i++) {
                    for (int t = turn * 2; t >= 0; t--) {
                        // 아래 방향
                        if (map[i][j][t][0] != INIT) {
                            if (i < M - 1) {
                                map[i + 1][j][t][0] = Math.min(map[i + 1][j][t][0], map[i][j][t][0] + heightCost[i][j]);
                            }
                            if (t != turn * 2 && j < N - 1) {
                                map[i][j + 1][t + 1][1] = Math.min(map[i][j + 1][t + 1][1], map[i][j][t][0] + widthCost[i][j]);
                            }
                        }

                        // 오른쪽 방향
                        if (map[i][j][t][1] != INIT) {
                            if (t != turn * 2 && i < M - 1) {
                                map[i + 1][j][t + 1][0] = Math.min(map[i + 1][j][t + 1][0], map[i][j][t][1] + heightCost[i][j]);
                            }
                            if (j < N - 1) {
                                map[i][j + 1][t][1] = Math.min(map[i][j + 1][t][1], map[i][j][t][1] + widthCost[i][j]);
                            }
                        }
                    }
                }
            }

            int minTurn = INIT;
            for (int t = 0; t < turn * 2 + 1; t++) {
                if (map[M - 1][N - 1][t][0] <= G) {
                    minTurn = Math.min(minTurn, t);
                }
                if (map[M - 1][N - 1][t][1] <= G) {
                    minTurn = Math.min(minTurn, t);
                }
            }
            if (minTurn == INIT) {
                bw.write("-1\n");
            } else {
                bw.write(((M + N - 2) * L + minTurn) + "\n");
            }
        }
            bw.flush();
            bw.close();
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
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
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
