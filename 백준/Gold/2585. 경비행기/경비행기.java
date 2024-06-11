import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[][] map;
    static int distance(Point p1, Point p2) {
        return (int)Math.ceil(Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2))/10);
    }
    static int n,k;

    static class Node {
        int now, depth, minCost;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("now=").append(now);
            sb.append(", depth=").append(depth);
            sb.append(", minCost=").append(minCost);
            sb.append('}');
            return sb.toString();
        }

        public Node(int now, int depth, int minCost) {
            this.now = now;
            this.depth = depth;
            this.minCost = minCost;
        }
    }
    public static void main(String[] args) throws IOException {

        Reader reader = new Reader();
         n = reader.nextInt();
         k = reader.nextInt();

        map = new int[n + 2][n + 2];
        Point[] points = new Point[n+2];
        points[0] = new Point(0, 0);
        points[n + 1] = new Point(10000, 10000);
        for (int i = 1; i <= n; i++) {

            int x = reader.nextInt();
            int y = reader.nextInt();

            points[i] = new Point(y, x);

        }
        for (int i = 0; i <= n+1; i++) {
            for (int j = i + 1; j <= n+1; j++) {
                //i랑j거리
                map[i][j] = distance(points[i], points[j]);
                map[j][i] = map[i][j];
            }
        }



        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.minCost));
        pq.add(new Node(0, 0, 0));
        int[] visited = new int[n + 2];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[0] = 0;
        while(!pq.isEmpty()) {
            final Node node = pq.poll();
            if(node.now==n+1) {
                System.out.println(node.minCost);
                return;
            }
            for (int i = 1; i <= n + 1; i++) {
                int newValue = Math.max(node.minCost, map[node.now][i]);
                if (visited[i] > newValue && node.depth <= k) {
                    visited[i] = newValue;
                    pq.add(new Node(i, node.depth + 1, newValue));
                    
                }
            }
        }
//
//
//        int lo = 0, hi = 15000;
//
//        while(lo+1<hi) {
//
//            int mid = (hi - lo) / 2 + lo;
//
//            if(check(mid)) {
//                hi = mid;
//            }
//            else {
//                lo = mid;
//            }
//        }
//
//        System.out.println(hi);

    }

    static class Phase {
        int now;
        public Phase(int now, int depth) {
            this.now = now;
            this.depth = depth;
        }

        int depth;

    }

    private static boolean check(int mid) {
        boolean[] visited = new boolean[n + 2];
        Deque<Phase> q = new ArrayDeque<>();
        q.add(new Phase(0, 0));
        visited[0] = true;

        while(!q.isEmpty()) {
            final Phase now = q.poll();

            if(now.now==n+1) {
                return true;
            }
            for (int i = 1; i <= n+1; i++) {
                if (map[now.now][i] <= mid && !visited[i]) {
                    if(now.depth <= k) {
                        if(i == n+1) {
                            return true;
                        }
                        q.add(new Phase(i, now.depth + 1));
                        visited[i] = true;
                    }
                }
            }
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
