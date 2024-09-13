
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    static Map<Integer, List<Edge>> map;

    static class Edge implements Comparable<Edge> {
        int to, distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = reader.nextInt();

        for (int tc = 0; tc < T; tc++) {
            map = new HashMap<>();
            int n = reader.nextInt();
            int m = reader.nextInt();
            int t = reader.nextInt();

            for(int i = 1;i<=n;i++) {
                map.put(i, new ArrayList<>());
            }

            int s = reader.nextInt();
            int g = reader.nextInt();
            int h = reader.nextInt();

            for (int i = 0; i < m; i++) {
                int a = reader.nextInt();
                int b = reader.nextInt();
                int d = reader.nextInt();

                map.get(a).add(new Edge(b, d));
                map.get(b).add(new Edge(a, d));
            }

            List<Integer> targets = new ArrayList<>();
            for(int i = 0;i<t;i++) {
                targets.add(reader.nextInt());
            }

            int[][] distanceS = new int[4][n + 1];
            int INIT = Integer.MAX_VALUE / 2;
            for(int i = 0;i<4;i++)
                Arrays.fill(distanceS[i], INIT);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            int bit = 0;
            if(s==g) {
                bit |= 2;
            } else if(s==h) {
                bit |= 1;
            }
            pq.add(new Node(s, 0,bit));
            distanceS[0][s] = 0;
            while(!pq.isEmpty()) {
                Node node = pq.poll();

                for (Edge edge : map.get(node.now)) {
                    int nextBit = node.bit;
                    if(edge.to == g) {
                        nextBit |= 2;
                    } else if(edge.to == h) {
                        nextBit |= 1;
                    }
                    if (distanceS[nextBit][edge.to] > node.value + edge.distance) {
                        distanceS[nextBit][edge.to] = node.value + edge.distance;
                        pq.add(new Node(edge.to, distanceS[nextBit][edge.to], nextBit));
                    }
                }
            }
            Collections.sort(targets);
            a: for (Integer target : targets) {
                if(distanceS[3][target]== INIT) continue;

                int should = distanceS[3][target];
                for(int i = 0;i<3;i++) {
                    if(distanceS[i][target] < should) continue a;
                }
                bw.write(target+" ");
            }

            bw.newLine();


        }
        bw.flush();bw.close();
    }

    static class Node implements Comparable<Node>  {
        int now, value;
        int bit;// bit 00, 01 10 11 = 3;

        public Node(int now, int value, int bit) {
            this.now = now;
            this.value = value;
            this.bit = bit;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
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
