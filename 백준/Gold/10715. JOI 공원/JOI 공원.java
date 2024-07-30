
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static Map<Integer, List<Edge>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N =Integer.parseInt(st.nextToken());
        int M =Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        for(int i =1;i<=N;i++) {
            map.put(i, new ArrayList<>());
        }

        long edgeSum = 0L;
        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map.get(a).add(new Edge(b, d));
            map.get(b).add(new Edge(a, d));
            edgeSum+=d;

        }

        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Edge(1, 0));
        dist[1] = 0;
        while(!pq.isEmpty()) {
            Edge poll = pq.poll();

            if(dist[poll.to] < poll.cost) continue;

            for(Edge edge : map.get(poll.to)) {
                if(dist[edge.to] > poll.cost + edge.cost) {
                    dist[edge.to] = poll.cost + edge.cost;
                    pq.add(new Edge(edge.to, dist[edge.to]));
                }
            }
        }

        List<Node> list = new ArrayList<>();
        for(int i =1;i<=N;i++) {
            list.add(new Node(i, dist[i]));
        }
        Collections.sort(list, Comparator.comparingInt(o -> o.cost));

        Set<Integer> connected = new HashSet<>();


        int before = 0;
        long sum = Long.MAX_VALUE;
        for (Node node : list) {

            if(before!=node.cost) {
                //calculate
                sum = Math.min(sum, (long) C * before + edgeSum);
                before = node.cost;
            }
            connected.add(node.index);
            for (Edge edge : map.get(node.index)) {
                if (connected.contains(edge.to)) {
                    edgeSum -= edge.cost;
                }
            }


        }
        sum = Math.min(sum, (long) C * before + edgeSum);
        System.out.println(sum);

    }
    static class Node {
        int index, cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
