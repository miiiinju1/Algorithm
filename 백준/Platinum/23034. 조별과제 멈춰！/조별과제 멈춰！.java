
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static Map<Integer, List<Node>> map = new HashMap<>();

    static int[][] cost;
    static int N,M;
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        N = reader.nextInt();
        M = reader.nextInt();

        parent = new int[N + 1];
        cost = new int[N + 1][N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        for(int i = 1;i<=N;i++) {
            parent[i] = i;
            map.put(i, new ArrayList<>());
        }

        for(int i = 0;i<M;i++) {
//            st = new StringTokenizer(br.readLine());

            int A = reader.nextInt();
            int B = reader.nextInt();
            int C = reader.nextInt();

            pq.add(new Edge(A, B, C));
        }

        long sum = 0;

        while(!pq.isEmpty()) {
            final Edge edge = pq.poll();

            if (union(edge.from, edge.to)) {
                sum += edge.cost;

                map.get(edge.from).add(new Node(edge.to, edge.cost));
                map.get(edge.to).add(new Node(edge.from, edge.cost));
            }
        }

        // 여기에서 모든 쌍에 대해 뺼 거 적어두기
        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
            bfs(i, cost[i]);
        }

        int Q = reader.nextInt();
        for (int i = 0; i < Q; i++) {
//            st = new StringTokenizer(br.readLine());
            int X = reader.nextInt();
            int Y = reader.nextInt();
            
            bw.write(sum - cost[X][Y] + "\n");

        }

        bw.flush();bw.close();

    }

    static void bfs(int start, int[] cost) {

        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(start, 0));

        cost[start] = 0;
        while(!q.isEmpty()) {
            final Point now = q.poll();
            for (Node node : map.get(now.to)) {
                int max = Math.max(now.max, node.cost);
                if (cost[node.to] > max) {
                    cost[node.to] = max;
                    q.add(new Point(node.to, max));
                }
            }
        }
    }

    static class Point {
        int to, max;

        public Point(int to, int max) {
            this.to = to;
            this.max = max;
        }
    }

    static class Node implements Comparable<Node> {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] parent;
    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            parent[fa] = fb;
            return true;
        }
        return false;
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
