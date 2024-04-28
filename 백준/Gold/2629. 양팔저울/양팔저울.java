import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader rd = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = rd.nextInt();
        int[] weights = new int[N];
        int[][] dp = new int[80001][N];
        for(int i= 0;i<N;i++) {
            weights[i] = rd.nextInt();
        }

        dp[40000][N-1] = 1;
        dp[weights[N-1]+40000][N-1] = 1;
        dp[40000-weights[N-1]][N-1] = 1;
        for(int i = N-2;i>=0;i--) {
            for(int j= 0;j<=80000;j++) {
                if(dp[j][i+1]==0) continue;

                dp[j][i] = 1;
                if(j-weights[i]>=0) {
                    dp[j-weights[i]][i] = 1;
                }
                if(j+weights[i]<=80000) {
                    dp[j+weights[i]][i] = 1;
                }
            }
        }

        int M = rd.nextInt();
        for(int i = 0;i<M;i++) {
            if(dp[rd.nextInt()+40000][0]==1) {
                bw.write("Y ");
            }
            else {
                bw.write("N ");
            }
        }
        bw.flush();bw.close();
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

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
