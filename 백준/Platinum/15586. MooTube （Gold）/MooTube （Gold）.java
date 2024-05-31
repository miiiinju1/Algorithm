
import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];
        Arrays.fill(size, 1);
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.value, o1.value));

        for(int i= 1;i<=N;i++) parent[i] = i;
        for(int i= 1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, v));

        }

        int[] result = new int[Q];
        PriorityQueue<Query> queries = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.k, o1.k));
//        List<Query> queries = new ArrayList<>(Q);
        for(int i= 0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            queries.add(new Query(k, q, i));

        }
        //큰 거 부터 연결시켜가면서

        int nowK = Integer.MAX_VALUE;
        while(!queries.isEmpty()) {

            final Query query = queries.poll();

//            System.out.println(query.k);
//            if (nowK > query.k) {
                while (!pq.isEmpty()) {
                    final Edge edge = pq.peek();

                    if (query.k > edge.value) {
                        break;
                    }
                    pq.poll();

                    union(edge.a, edge.b);
                }
//            }

            result[query.index] = size[find(query.v)]-1;
        }

//        for(int i= 1;i<=N;i++) {
//            System.out.print(parent[i]+" ");
//        }
//        System.out.println();
       for(int i= 0;i<Q;i++) {
           bw.write(result[i] + "\n");
//           System.out.println(result[i]);

       }
       bw.flush();bw.close();






    }
}
