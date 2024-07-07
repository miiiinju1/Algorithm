
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int to, cost, time;

        public Edge(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    static Map<Integer, ArrayList<Edge>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(c<=M)
                map.get(u).add(new Edge(v, c, d));
        }

        for(int i= 1;i<=N;i++) {
            map.get(i).sort((o1, o2) -> {
                if(o1.time==o2.time) {
                    return Integer.compare(o1.cost, o2.cost);
                }
                return Integer.compare(o1.time, o2.time);
            });
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));

        int[][] time = new int[N + 1][M + 1];
        for(int i= 0;i<=N;i++)
        Arrays.fill(time[i], Integer.MAX_VALUE);
        time[1][0] = 0;

        while(!pq.isEmpty()) {
            final Node now = pq.poll();

            for (Edge edge : map.get(now.to)) {
                int nextCost = now.cost + edge.cost;
                int nextTime = now.time + edge.time;
                if (nextCost <= M&&time[edge.to][nextCost] > nextTime) {
                    for (int i = nextCost; i <= M; i++) {
                        if (nextTime < time[edge.to][i]) {
                            time[edge.to][i] = nextTime;
                        } else {
                            break;
                        }
                    }
                    pq.add(new Node(edge.to, now.cost + edge.cost, now.time + edge.time));



                }

            }
        }

        int min = Integer.MAX_VALUE;
        for(int i= 0;i<=M;i++) {
            min = Math.min(min, time[N][i]);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println("Poor KCM");
            return;
        }
        System.out.println(min);




    }
    static class Node implements Comparable<Node> {
        int to, cost, time;

        public Node(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
