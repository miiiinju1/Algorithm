import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
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
    static class Store implements Comparable<Store> {
        int y, x, i;
        int gValue; // 현재 노드까지의 비용
        int fValue; // f(N) 값은 gValue와 휴리스틱 값의 합

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Store{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append('}');
            return sb.toString();
        }
        @Override
        public int compareTo(Store o) {
            return this.fValue - o.fValue;
        }

        // fValue 계산을 위한 함수
        public void calculateFValue(int destY, int destX) {
            int hValue = Math.abs(this.y - destY) + Math.abs(this.x - destX); // 휴리스틱 함수 (맨해튼 거리)
            this.fValue = this.gValue + hValue;
        }

        public Store(int y, int x,int i) {
            this.y = y;
            this.x = x;
            this.i=i;
        }
        private int heuristic(Store destination) {
            return Math.abs(this.y - destination.y) + Math.abs(this.x - destination.x);
        }
    }
    public static void main(String[] args) throws IOException {
        Reader input = new Reader();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = input.nextInt();
        a : for (int tc = 0; tc < T; tc++) {
            int N = input.nextInt();;

            int startY = input.nextInt();
            int startX = input.nextInt();
            ArrayList<Store> stores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                stores.add(new Store(input.nextInt(), input.nextInt(),-1));
            }
            int destY = input.nextInt();
            int destX = input.nextInt();
            if(Math.abs(startX-destX)+Math.abs(startY-destY)<=1000) {
                bw.write("happy\n");
                continue a;
            }
            boolean[] visited = new boolean[N];

            PriorityQueue<Store> q = new PriorityQueue<>();
            Store start = new Store(startY, startX, -1);
            start.gValue = 0;
            start.calculateFValue(destY, destX);
            q.add(start);

            while(!q.isEmpty()) {
                Store now = q.poll();
                int nowY = now.y;
                int nowX = now.x;
                if (now.i != -1) {
                    if (!visited[now.i]) {
                        visited[now.i] = true;
                    }
                }
                if (Math.abs(nowX - destX) + Math.abs(nowY - destY) <= 1000) {
                    bw.write("happy\n");
                    continue a;
                }

                for (int i = 0; i < N; i++) {
                    if (!visited[i] && Math.abs(nowX - stores.get(i).x) + Math.abs(nowY - stores.get(i).y) <= 1000) {
                        Store next = new Store(stores.get(i).y, stores.get(i).x, i);
                      //  next.gValue = now.gValue + now.heuristic(next);
//next.calculateFValue(destY, destX);
                        q.add(next);

                    }
                }
//
            }
            bw.write("sad\n");



        }

        bw.flush();bw.close();
    }
}


//2
//2
//2000 1000
//1000 0
//1000 1000
//2000 1000
//2
//0 0
//1000 0
//2000 1000
//2000 2000

//1
//100
//-300 -400
//0 0
//0 1000
//0 2000
//0 3000
//0 4000
//0 5000
//0 6000
//0 7000
//0 8000
//0 9000
//1000 0
//1000 1000
//1000 2000
//1000 3000
//1000 4000
//1000 5000
//1000 6000
//1000 7000
//1000 8000
//1000 9000
//2000 0
//2000 1000
//2000 2000
//2000 3000
//2000 4000
//2000 5000
//2000 6000
//2000 7000
//2000 8000
//2000 9000
//3000 0
//3000 1000
//3000 2000
//3000 3000
//3000 4000
//3000 5000
//3000 6000
//3000 7000
//3000 8000
//3000 9000
//4000 0
//4000 1000
//4000 2000
//4000 3000
//4000 4000
//4000 5000
//4000 6000
//4000 7000
//4000 8000
//4000 9000
//5000 0
//5000 1000
//5000 2000
//5000 3000
//5000 4000
//5000 5000
//5000 6000
//5000 7000
//5000 8000
//5000 9000
//6000 0
//6000 1000
//6000 2000
//6000 3000
//6000 4000
//6000 5000
//6000 6000
//6000 7000
//6000 8000
//6000 9000
//7000 0
//7000 1000
//7000 2000
//7000 3000
//7000 4000
//7000 5000
//7000 6000
//7000 7000
//7000 8000
//7000 9000
//8000 0
//8000 1000
//8000 2000
//8000 3000
//8000 4000
//8000 5000
//8000 6000
//8000 7000
//8000 8000
//8000 9000
//9000 0
//9000 1000
//9000 2000
//9000 3000
//9000 4000
//9000 5000
//9000 6000
//9000 7000
//9000 8000
//9000 9000
//9500 9500