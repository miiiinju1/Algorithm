
import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int a, b, value;

        public Edge(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }
    }
    static class Query {
        int k, v, index;

        public Query(int k, int v, int index) {
            this.k = k;
            this.v = v;
            this.index = index;
        }
    }

    static int[] parent;
    static int[] size;
    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    static void union(int a, int b) {

        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            parent[fa] = fb;

            size[fb] += size[fa];
            size[fa] = 0;
        }
    }


    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = reader.nextInt();
        int Q =  reader.nextInt();
        parent = new int[N + 1];
        size = new int[N + 1];
        Arrays.fill(size, 1);
        List<Edge> list = new ArrayList<>(N);
//        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.value, o1.value));

        for(int i= 1;i<=N;i++) parent[i] = i;
        for (int i = 1; i < N; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int v = reader.nextInt();

            list.add(new Edge(a, b, v));

        }
        list.sort((o1, o2) -> Integer.compare(o2.value, o1.value));

        int[] result = new int[Q];

//        PriorityQueue<Query> queries = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.k, o1.k));
        List<Query> queries = new ArrayList<>(Q);
        for(int i= 0;i<Q;i++) {

            int k = reader.nextInt();
            int q = reader.nextInt();

            queries.add(new Query(k, q, i));

        }
        queries.sort((o1, o2) -> Integer.compare(o2.k, o1.k));

        //큰 거 부터 연결시켜가면서

        int index = 0;
        for (Query query : queries) {
//            System.out.println(query.k);
//            if (nowK > query.k) {
            while(index<list.size()) {
                final Edge edge = list.get(index);
                if (query.k > edge.value) {
                    break;
                }
                ++index;
                union(edge.a, edge.b);
            }

            result[query.index] = size[find(query.v)]-1;
        }

       for(int i= 0;i<Q;i++) {
           bw.write(result[i] + "\n");
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
