
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static Map<Integer, PriorityQueue<Edge>> map = new HashMap<>();


    static class Node implements Comparable<Node> {
        int now;
        long cost;

        public Node(int now, long cost) {
            this.now = now;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException {

        Reader reader = new Reader();
        int N = reader.nextInt();
        int M = reader.nextInt();
        int K = reader.nextInt();


        for (int i = 1; i <= N; ++i) {
            map.put(i, new PriorityQueue<>());
        }

        for(int i= 0;i<M;++i) {

            int u = reader.nextInt();
            int v = reader.nextInt();
            int c = reader.nextInt();

            // u -> v

            map.get(v).add(new Edge(u, c));
        }

        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < K; ++i) {
            int l = reader.nextInt();
            pq.add(new Node(l, 0));
            distance[l] = 0;
        }

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(distance[now.now] < now.cost) {
                continue;
            }

            for (Edge edge : map.get(now.now)) {
                long next = now.cost + edge.cost;
                if(distance[edge.to] > next) {
                    distance[edge.to] = next;
                    pq.add(new Node(edge.to, next));
                }
            }
        }


        long max = 0;
        int maxCity = -1;

        for(int i = 1;i<=N;++i) {
            if(distance[i] > max) {
                max = distance[i];
                maxCity = i;
            }
        }

        System.out.println(maxCity);
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
