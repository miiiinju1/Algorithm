import java.io.BufferedReader;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        for (int i = 1; i <= N; ++i) {
            map.put(i, new PriorityQueue<>());
        }

        for(int i= 0;i<M;++i) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // u -> v

            map.get(v).add(new Edge(u, c));
        }

        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; ++i) {
            int l = Integer.parseInt(st.nextToken());
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

}
