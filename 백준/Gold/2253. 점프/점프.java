import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();

        int N = reader.nextInt();
        int M = reader.nextInt();

        int[][] dp = new int[N + 1][150];

        for(int i= 0;i<=N;i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][0] = 0;

        boolean[] visited = new boolean[N + 1];

        for(int i= 0;i<M;i++) {
            int cant = reader.nextInt();
            visited[cant] = true;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 150; j++) {
                if (dp[i][j] != Integer.MAX_VALUE) {
                    // 속도 1줄이기 전
                    if (j - 1 >= 1) {
                        if (i + j - 1 <= N && !visited[i + j - 1]) {
                            dp[i + j - 1][j - 1] = Math.min(dp[i + j - 1][j - 1], dp[i][j] + 1);
                        }
                    }
                    // 그대로
                    if (i + j <= N && !visited[i + j]) {
                        dp[i + j][j] = Math.min(dp[i + j][j], dp[i][j] + 1);
                    }
                    // 속도 1 늘리기
                    if (j + 1 < 150) {
                        if (i + j + 1 <= N && !visited[i + j + 1]) {
                            dp[i + j + 1][j + 1] = Math.min(dp[i + j + 1][j + 1], dp[i][j] + 1);
                        }
                    }
                }
//                System.out.print(dp[i][j] + " ");
            }
//            System.out.println();
        }

        int min = Integer.MAX_VALUE;
        for(int i= 0;i<150;i++) {
            min = Math.min(min, dp[N][i]);
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

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
