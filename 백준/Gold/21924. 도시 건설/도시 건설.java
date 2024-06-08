
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int a,b, cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

//        public Edge(int next, int cost) {
//            this.next = next;
//            this.cost = cost;
//        }

        @Override
        public int compareTo(Edge o) {

            return Integer.compare(this.cost, o.cost);
        }
    }

    static int find(int v) {
        if(parent[v] == v) {
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

//    static Map<Integer, PriorityQueue<Edge>> map = new HashMap<>();
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
//            map.put(i, new PriorityQueue<>());
        }


        PriorityQueue<Edge> pq = new PriorityQueue<>();

        long origin = 0L;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            origin+=c;
            pq.add(new Edge(a, b, c));
//            map.get(a).add(new Edge(b, c));
//            map.get(b).add(new Edge(a, c));
        }


//        pq.add(new Edge(1, 0));

        long sum = 0L;
        int count = 0;

        while(!pq.isEmpty()) {
            final Edge now = pq.poll();
            if (union(now.a, now.b)) {
                count++;
                sum += now.cost;
            }

        }

        if(count!=N-1) {
            System.out.println(-1);
            return;
        }

        System.out.println(origin-sum);


    }
}
