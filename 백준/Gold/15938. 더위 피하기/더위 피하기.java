import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static class Obstacle {
        int y, x;

        public Obstacle(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Obstacle obstacle = (Obstacle) o;

            if (y != obstacle.y) return false;
            return x == obstacle.x;
        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            return result;
        }
    }


    static int[][][] dp;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static final int MOD = 1000000007;

    static int dfs(int y, int x, int depth) {
        if (depth > T) {
            dp[y][x][depth] = 0;
            return 0;
        }
        if(dp[y][x][depth]!=-1) {
            return dp[y][x][depth];
        }

        if (startY + (y - 200) == endY && startX + (x - 200) == endX) {
            return 1;
        }
        int result = 0;
        for (int d = 0; d < 4; d++) {
            int Y = dy[d] + y;
            int X = dx[d] + x;
            if (isValid(Y, X) && dp[Y][X][depth + 1] != 0) {
                result = (result + dfs(Y, X, depth + 1)) % MOD;
            }
        }
        dp[y][x][depth] = result;
        return result;
    }

    static boolean isValid(int y, int x) {
        return y >= 0
                && x >= 0
                && y <= 400
                && x <= 400;
    }

    static Set<Obstacle> obstacles = new HashSet<>();
    //    static HashMap<Point, Integer> dp;
    static int startY, startX, endY, endX, T;

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        startX = reader.nextInt() ;
        startY = reader.nextInt() ;

        T = reader.nextInt();

        dp = new int[401][401][202];
        for (int i = 0; i <= 400; i++) {
            for (int j = 0; j <= 400; j++) {
                for (int k = 0; k <= 200; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        endX = reader.nextInt() ;
        endY = reader.nextInt() ;

        int N = reader.nextInt();

        for (int i = 0; i < N; i++) {
            int x = reader.nextInt() ;
            int y = reader.nextInt() ;

            if (Math.abs(y - startY) <= 200 && Math.abs(x - startX) <= 200) {
                for(int t = 0;t<=200;t++)
                    dp[y-startY+200][x-startX+200][t] = 0;
            }

        }

        System.out.println(dfs(200, 200, 0));
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
