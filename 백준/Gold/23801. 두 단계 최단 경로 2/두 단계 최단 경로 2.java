
import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Edge {
        int next, value;

        public Edge(int next, int value) {
            this.next = next;
            this.value = value;
        }

    }
    static class Node implements Comparable<Node> {
        int now;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("now=").append(now);
            sb.append(", value=").append(value);
            sb.append(", visited=").append(visited);
            sb.append('}');
            return sb.toString();
        }

        long value;
        boolean visited;

        public Node(int now, long value, boolean visited) {
            this.now = now;
            this.value = value;
            this.visited = visited;
        }

        public Node(int now, long value) {
            this.now = now;
            this.value = value;
            this.visited = false;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.value, o.value);
        }
    }
    static Map<Integer, List<Edge>> map = new HashMap<>(100000);

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();


        int N = reader.nextInt();
        int M = reader.nextInt();

        for(int i= 1;i<=N;i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i= 0;i<M;i++) {

            int u = reader.nextInt();
            int v = reader.nextInt();
            int w = reader.nextInt();

            map.get(u).add(new Edge(v, w));
            map.get(v).add(new Edge(u, w));

        }

        int X = reader.nextInt();
        int Z = reader.nextInt();

        int P = reader.nextInt();

        Set<Integer> mustVisit = new HashSet<>();
        for(int i= 0;i<P;i++) {
            mustVisit.add(reader.nextInt());

        }


        long[][] visited = new long[2][N + 1];
        Arrays.fill(visited[0], Long.MAX_VALUE);
        Arrays.fill(visited[1], Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited[0][0] = 0;
        pq.add(new Node(X, 0));


        while(!pq.isEmpty()) {
            final Node now = pq.poll();
//            System.out.println("now = " + now);

            if(now.now==Z) {
                if (now.visited) {
                    System.out.println(now.value);
                    return;
                }
//                continue;
            }


            for (Edge edge : map.get(now.now)) {
                if(now.visited) {
                    if (visited[1][edge.next] > now.value + edge.value) {
                        visited[1][edge.next] = now.value + edge.value;
                        pq.add(new Node(edge.next, now.value + edge.value, now.visited));
                    }
                }
                else {
                    if (visited[0][edge.next] > now.value + edge.value) {
                        visited[0][edge.next] = now.value + edge.value;
                        final Node node = new Node(edge.next, now.value + edge.value, now.visited);

                        if (!node.visited && mustVisit.contains(edge.next)) {
                            node.visited = true;
                        }
                        pq.add(node);
                    }
//                    else if(mustVisit.contains(edge.next)) {
//                        if (visited[1][edge.next] > now.value + edge.value) {
//                            visited[1][edge.next] = now.value + edge.value;
//                            final Node node = new Node(edge.next, now.value + edge.value, true);
//                            pq.add(node);
//                        }
//                    }
                }


            }

        }
        System.out.println(-1);



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
