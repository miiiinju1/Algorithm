
import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static Map<Integer, List<Node>> map = new HashMap<>();

    static int[][] cost;
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        cost = new int[N + 1][N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        for(int i = 1;i<=N;i++) {
            parent[i] = i;
            map.put(i, new ArrayList<>());
        }

        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.add(new Edge(A, B, C));
        }

        long sum = 0;

        while(!pq.isEmpty()) {
            final Edge edge = pq.poll();

            if (union(edge.from, edge.to)) {
                sum += edge.cost;

                map.get(edge.from).add(new Node(edge.to, edge.cost));
                map.get(edge.to).add(new Node(edge.from, edge.cost));
            }
        }

        // 여기에서 모든 쌍에 대해 뺼 거 적어두기
        // 반까지만 하고 나중에 찾을 때 순서 바꿔서 검색하면 줄일 수 있음
        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
            bfs(i, cost[i]);
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            bw.write(sum - cost[X][Y] + "\n");

        }

        bw.flush();bw.close();

    }

    static void bfs(int start, int[] cost) {

        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(start, 0));

        cost[start] = 0;
        while(!q.isEmpty()) {
            final Point now = q.poll();
            for (Node node : map.get(now.to)) {
                int max = Math.max(now.max, node.cost);
                if (cost[node.to] > max) {
                    cost[node.to] = max;
                    q.add(new Point(node.to, max));
                }
            }
        }
    }

    static class Point {
        int to, max;

        public Point(int to, int max) {
            this.to = to;
            this.max = max;
        }
    }

    static class Node implements Comparable<Node> {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] parent;
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


}
