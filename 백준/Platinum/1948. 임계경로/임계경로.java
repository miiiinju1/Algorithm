import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Edge {
        int to, cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int n, m;
    static List<List<Edge>> graph = new ArrayList<>();
    static int[] dist;
    static List<Integer>[] reverseGraph;
    static int start, end;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        n = reader.nextInt();
        m = reader.nextInt();
        
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        reverseGraph = new List[n + 1];
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int c = reader.nextInt();
            graph.get(a).add(new Edge(b, c));
            reverseGraph[b].add(a);
        }

        start = reader.nextInt();
        end = reader.nextInt();

        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[start] = 0;

        dijkstra();
        int maxTime = dist[end];
        int criticalPathCount = countCriticalPaths();

        System.out.println(maxTime);
        System.out.println(criticalPathCount);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> b.cost - a.cost);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currNode = current.to;
            int currDist = current.cost;

            if (currDist < dist[currNode]) continue;

            for (Edge edge : graph.get(currNode)) {
                int nextNode = edge.to;
                int newDist = currDist + edge.cost;

                if (newDist > dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.offer(new Edge(nextNode, newDist));
                }
            }
        }
    }

    static int countCriticalPaths() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(end);
        visited[end] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int prev : reverseGraph[curr]) {
                if (dist[prev] + findEdgeCost(prev, curr) == dist[curr]) {
                    count++;
                    if (!visited[prev]) {
                        visited[prev] = true;
                        queue.offer(prev);
                    }
                }
            }
        }
        return count;
    }

    static int findEdgeCost(int from, int to) {
        for (Edge edge : graph.get(from)) {
            if (edge.to == to) {
                return edge.cost;
            }
        }
        return 0;
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
