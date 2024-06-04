
import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int next;
        long value;

        public Edge(int next, long value) {
            this.next = next;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.value, o.value);
        }
    }

    static Map<Integer, List<Edge>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int[] energy = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            energy[i] = reader.nextInt();
            map.put(i, new ArrayList<>());
        }

        long[] visited = new long[n + 1];
        Arrays.fill(visited, Long.MAX_VALUE);
//        int[][] value = new int[n + 1][n + 1];
//        for(int i = 0;i<=n;i++) {
//            Arrays.fill(value[i], Integer.MAX_VALUE);
//        }
        for (int i = 1; i < n; i++) {

            int a = reader.nextInt();
            int b = reader.nextInt();
            int v = reader.nextInt();

//            value[a][b] = v;;
//            value[b][a] = v;;
            map.get(a).add(new Edge(b, v));
            map.get(b).add(new Edge(a, v));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        visited[1] = 0;

        int[][] prev = new int[n + 1][2];

        prev[1][0] = -1;
        while(!pq.isEmpty()) {
            final Edge now = pq.poll();

            for (Edge edge : map.get(now.next)) {

                if (visited[edge.next] > now.value + edge.value) {
                    prev[edge.next][0] = now.next;
                    prev[edge.next][1] = (int) edge.value;
                    visited[edge.next] = now.value + edge.value;
                    pq.add(new Edge(edge.next, now.value + edge.value));
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        a: for (int i = 1; i <= n; i++) {

            int temp = i;
            int length = 0;
            while(temp!=1) {

                length += prev[temp][1];
                if(length>energy[i]) {
                    bw.write(temp + "\n");
                    continue a;
                }
                temp = prev[temp][0];
            }
        bw.write("1\n");


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
