
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int to, length, toll;

        public Edge(int to, int length, int toll) {
            this.to = to;
            this.length = length;
            this.toll = toll;
        }
    }

    static Map<Integer, List<Edge>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int R = Integer.parseInt(br.readLine());
        for(int i =1;i<=N;i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i =0;i<R;i++) {
            var st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            map.get(s).add(new Edge(d, l, t));
        }

//        int[][] visited = new int[2][N + 1];
//        Arrays.fill(visited[0], Integer.MAX_VALUE);
//        Arrays.fill(visited[1], Integer.MAX_VALUE);

        int[][] distance = new int[N + 1][K + 1];
        for(int i = 0;i<=N;i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(1, 0, 0));
        distance[1][0] = 0;
//        visited[0][1] = 0;
//        visited[1][1] = 0;

        int min = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.now == N) {
                min = Math.min(min, node.distance);
                continue;
            }

            for (Edge edge : map.get(node.now)) {
                int cost = node.cost + edge.toll;
                int dist = node.distance + edge.length;

                if(cost > K) continue;

                if(distance[edge.to][cost] > dist) {
                    distance[edge.to][cost] = dist;
                    pq.add(new Node(edge.to, cost, dist));
                }
//                if (visited[1][edge.to] > dist || visited[0][edge.to] > cost) {
//                    visited[0][edge.to] = Math.min(visited[0][edge.to], cost);
//                    visited[1][edge.to] = Math.min(visited[1][edge.to], dist);
//                    pq.add(new Node(edge.to, cost, dist));
//                }
            }

        }
        if(min==Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);

    }

    static class Node implements Comparable<Node> {
        int now;
        int cost, distance;

        public Node(int now, int cost, int distance) {
            this.now = now;
            this.cost = cost;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if(this.distance == o.distance) {
                return Integer.compare(this.cost, o.cost);
            }
            return Integer.compare(this.distance, o.distance);
        }
    }
}
//1
//2
//2
//1 2 100 1
//1 2 1 100