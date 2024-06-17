
import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    static class Edge {
        int to,cost;
        int num;

        public Edge(int to, int cost, int num) {
            this.to = to;
            this.cost = cost;
            this.num = num;
        }
    }

    static class Node {
        int now, value;

        int num;

        public Node(int now, int value, int num) {
            this.now = now;
            this.value = value;
            this.num = num;
        }
    }

    static int[] visited;
    static int start, end,n,m;

    static Map<Integer, List<Edge>> map = new HashMap<>();
    static Map<Integer, List<Edge>> reverseMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
         n = reader.nextInt();
         m = reader.nextInt();

        for(int i= 0;i<=n;i++) {
            map.put(i, new ArrayList<>());
            reverseMap.put(i, new ArrayList<>());

        }
        for(int i= 0;i<m;i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int c = reader.nextInt();

            map.get(a).add(new Edge(b, c, i));
            reverseMap.get(b).add(new Edge(a, c, i));
        }

        start = reader.nextInt();
        end = reader.nextInt();

        Deque<Node> pq = new ArrayDeque<>();//(o1, o2) -> Integer.compare(o2.value, o1.value));
        visited = new int[n + 1];
        visited[start] = 0;

        pq.add(new Node(start, 0, -1));
        while(!pq.isEmpty()) {
            final Node now = pq.poll();
            if (now.value < visited[now.now]) continue;

            for (Edge edge : map.get(now.now)) {
                if(visited[edge.to] < now.value + edge.cost) {
                    visited[edge.to] = now.value + edge.cost;
                    pq.add(new Node(edge.to, now.value + edge.cost, edge.num));
                }
            }

        }


        boolean[] reverseVisited = new boolean[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(end);
        reverseVisited[end] = true;
        int count = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Edge edge : reverseMap.get(now)) {
                if (visited[edge.to] + edge.cost == visited[now]) {
                    count++;
                    if (!reverseVisited[edge.to]) {
                        reverseVisited[edge.to] = true;
                        q.add(edge.to);
                    }
                }
            }
        }

        System.out.println(visited[end]);
        System.out.println(count);

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

//5
//7
//1 2 1
//1 3 3
//2 3 2
//2 4 1
//2 5 3
//3 5 1
//4 5 1
//1 5


//5
//7
//1 2 1
//1 3 3
//2 3 2
//2 4 1
//4 5 1
//3 5 1
//2 5 3
//1 5

//4 4
//1 2 1
//2 3 1
//3 4 1
//2 4 2
//1 4