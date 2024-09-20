
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int cost,to;

        public Edge(int to, int cost) {
            this.cost = cost;
            this.to = to;
        }
    }

    static class Node {
        int now, cost;

        public Node(int now, int cost) {
            this.now = now;
            this.cost = cost;
        }
    }

    static Map<Integer, List<Edge>> originalMap = new HashMap<>();
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=  1;i<=N;++i) {
            originalMap.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            originalMap.get(a).add(new Edge(b, cost));
            originalMap.get(b).add(new Edge(a, cost));

        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        distance = new int[N + 1];
        int[] prev = new int[N + 1];
        Arrays.fill(prev, -1);
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            for (Edge edge : originalMap.get(node.now)) {

                int value = edge.cost + node.cost;

                if(distance[edge.to] > value) {
                    distance[edge.to] = value;
                    prev[edge.to] = node.now;
                    pq.add(new Node(edge.to, value));
                }
            }
        }

        List<int[]> edges = new ArrayList<>();
        int now = N;
        for (; ; ) {
            int next = prev[now];

            // next랑 now edge가 경로
            if(next==-1) {
                break;
            }
            edges.add(new int[]{now, next});
            now = next;
        }


        int max = 0;
        for (int[] edge : edges) {
            max = Math.max(max, dijkstra(edge));
        }

        System.out.println(max);
    }

    static int dijkstra (int[] exclude) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {

            Node node = pq.poll();

            for (Edge edge : originalMap.get(node.now)) {

                if(edge.to == exclude[0]) {
                    if(node.now == exclude[1]) {
                        continue;
                    }
                } else if(edge.to == exclude[1]) {
                    if(node.now == exclude[0]) {
                        continue;
                    }
                }
                int value = edge.cost + node.cost;

                if(distance[edge.to] > value) {
                    distance[edge.to] = value;
                    pq.add(new Node(edge.to, value));
                }
            }
        }
        return distance[distance.length - 1];
    }

}
