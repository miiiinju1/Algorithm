
import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int now, value;

        public Node(int now, int value) {
            this.now = now;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }

    static Map<Integer, List<Edge>> map = new HashMap<>();

    static int[] dist;
    static int[] cCount;
    static long[] dp;
    static int n;
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
             n = reader.nextInt();

            if (n == 0) {
                break;
            }
            map = new HashMap<>();
            for(int i= 0;i<n;i++) {
                map.put(i, new ArrayList<>());
            }

            for(int i= 1;i<n;i++) {
                int a = reader.nextInt();
                int b = reader.nextInt();
                int w = reader.nextInt();
                map.get(a).add(new Edge(b, w));
                map.get(b).add(new Edge(a, w));
            }

            dist = new int[n];
            cCount = new int[n];
            dp = new long[n];
            bfs();

            visited = new boolean[n];
            visited[0] = true;
            childCount(0);

            long sum = 0;
            for (int i = 0; i < n; i++) sum += dist[i];

//            System.out.println("sum = " + sum);
            dp[0] = sum;
            final long result = search(0, sum);

            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
    static boolean[] visited;

    static long search(int start, long sum) {
        visited = new boolean[n];
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(start, 0));

        visited[start] = true;
        long min = sum;
        while(!q.isEmpty()) {
            final Node node = q.poll();

            for (Edge edge : map.get(node.now)) {
                if(!visited[edge.to]) {
                    min = Math.min(min, check(node.now, edge.to, edge.cost));
                    visited[edge.to] = true;
                    q.add(new Node(edge.to, edge.cost + node.value));
                }
            }
        }
        return min;
    }
    static long check(int from, int to, int edgeCost) {
        long temp = dp[from];
        dp[to] = temp + (n - (cCount[to]) * 2L) * edgeCost;
        return temp;
    }

    static int childCount(int now) {

        int sum = 1;
        if (map.get(now).size() == 1 && now != 0) {
            cCount[now] = sum;
            return sum;
        }
        for (Edge edge : map.get(now)) {
            if(!visited[edge.to]) {
                visited[edge.to]= true;
                sum += childCount(edge.to);
            }
        }
        cCount[now] = sum;
        return sum;
    }
    static void bfs() {
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Node> pq = new ArrayDeque<>();
        dist[0] = 0;
        pq.add(new Node(0, 0));

        while(!pq.isEmpty()) {
            final Node now = pq.poll();

            for (Edge edge : map.get(now.now)) {
                if(dist[edge.to] > now.value+edge.cost) {
                    dist[edge.to] = now.value + edge.cost;
                    pq.add(new Node(edge.to, now.value + edge.cost));
                }
            }
        }

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
