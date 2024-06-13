
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    static int find(int v) {
        if(parent[v]==v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa!=fb) {
            parent[fa] = fb;
            return true;
        }
        return false;
    }

    static class Edge implements Comparable<Edge> {
        int a, b,cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i= 1;i<=N;i++) {
            parent[i] = i;
        }
        for(int i = 0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, c));
        }

        long sum = 0;
        while(!pq.isEmpty()) {
            final Edge edge = pq.poll();

            if (union(edge.a, edge.b)) {
                sum += edge.cost;
            }
        }

        System.out.println(sum);

    }
}
