
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        for(int i= 1;i<=V;++i) {
            map.put(i, new PriorityQueue<>());
        }

        st = new StringTokenizer(br.readLine());

        int kist = Integer.parseInt(st.nextToken());
        int cr = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] team = new int[N];
        for (int i = 0; i < N; ++i) {
            team[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            map.get(a).add(new Edge(b, l));
            map.get(b).add(new Edge(a, l));
        }

        // kist에서 시작하는 최단 거리

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[][] distance = new int[2][V + 1];
        Arrays.fill(distance[0], Integer.MAX_VALUE);
        Arrays.fill(distance[1], Integer.MAX_VALUE);

        pq.add(new Node(kist, 0));
        distance[0][kist] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            for (Edge edge : map.get(now.now)) {

                int next = edge.cost + now.cost;

                if(distance[0][edge.to]> next) {
                    distance[0][edge.to] = next;
                    pq.add(new Node(edge.to, next));
                }
            }
        }

        distance[1][cr] = 0;
        pq.add(new Node(cr, 0));
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            for (Edge edge : map.get(now.now)) {

                int next = edge.cost + now.cost;

                if(distance[1][edge.to]> next) {
                    distance[1][edge.to] = next;
                    pq.add(new Node(edge.to, next));
                }
            }
        }

        int result = 0;
        for (int i : team) {

            int a = distance[0][i] == Integer.MAX_VALUE ? -1 : distance[0][i];
            int b = distance[1][i] == Integer.MAX_VALUE ? -1 : distance[1][i];
            result += (a + b);

        }

        System.out.println(result);


    }
    static class Node implements Comparable<Node> {
        int now, cost;

        public Node(int now, int cost) {
            this.now = now;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

}
