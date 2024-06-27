import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;

        int count;

        public Node(int to, int count) {
            this.to = to;
            this.count = count;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.count, o.count);
        }
    }

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

    static Map<Integer, List<Edge>> map = new HashMap<>();

    static int N, P, K;

    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        count = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.get(a).add(new Edge(b, c));
            map.get(b).add(new Edge(a, c));
        }

        if(map.get(N).isEmpty()) {
            System.out.println(-1);
            return;
        }

        int lo = -1, hi = 1_000_001;

        while (lo + 1 < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (check(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        if (hi == 1_000_001 && !check(hi)) {
            System.out.println(-1);
            return;
        }
        System.out.println(hi);


    }

    private static boolean check(int mid) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(count, Integer.MAX_VALUE);
        count[1] = 0;
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.to == N) {
                continue;
            }
            if (count[now.to] < now.count)
                continue;

            for (Edge edge : map.get(now.to)) {
                int nextCount = edge.cost > mid ? now.count + 1 : now.count;
                if (count[edge.to] > nextCount) {
                    count[edge.to] = nextCount;
                    pq.add(new Node(edge.to, nextCount));
                }
            }
        }

        return count[N] <= K;
    }
}
