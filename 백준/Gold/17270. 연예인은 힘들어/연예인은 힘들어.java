
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
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static Map<Integer, List<Edge>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= V; ++i) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map.get(a).add(new Edge(b, c));
            map.get(b).add(new Edge(a, c));

        }

        st = new StringTokenizer(br.readLine());
        int j = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[][] distance = new int[2][V + 1];
        Arrays.fill(distance[0], Integer.MAX_VALUE);
        Arrays.fill(distance[1], Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[0][j] = 0;
        pq.add(new Node(j, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            for (Edge edge : map.get(now.now)) {
                int next = edge.cost + now.cost;
                if (distance[0][edge.to] > next) {
                    distance[0][edge.to] = next;
                    pq.add(new Node(edge.to, next));
                }
            }

        }

        pq.add(new Node(s, 0));
        distance[1][s] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            for (Edge edge : map.get(now.now)) {
                int next = edge.cost + now.cost;
                if (distance[1][edge.to] > next) {
                    distance[1][edge.to] = next;
                    pq.add(new Node(edge.to, next));
                }
            }

        }

        int min = Integer.MAX_VALUE;
        int jDistance = Integer.MAX_VALUE;

        List<Integer> minList = new ArrayList<>();

        for(int i = 1;i<=V;++i) {
            if(i==j || i==s) continue;

            if(distance[0][i] == Integer.MAX_VALUE || distance[1][i] == Integer.MAX_VALUE) continue;
            int sum = distance[0][i] + distance[1][i];

            if (min > sum) {
                minList = new ArrayList<>();
                min = sum;
            }
            if(min==sum) {
                minList.add(i);
            }
        }

        List<Integer> ha = new ArrayList<>();

        for (Integer i : minList) {

            // 조건 3
            if (distance[0][i] <= distance[1][i]) {


                if(distance[0][i] < jDistance) {
                    ha = new ArrayList<>();
                    jDistance = distance[0][i];
//                    answer = i;
                }
                if (jDistance == distance[0][i]) {
                    ha.add(i);
                }
            }


        }

        if(ha.isEmpty()) {
            System.out.println(-1);
            return;
        }

        System.out.println(ha.stream().min(Integer::compare).get());





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
