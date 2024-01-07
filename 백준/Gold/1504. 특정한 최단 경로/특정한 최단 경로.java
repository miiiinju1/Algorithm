import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int next;
            long weight;

        public Node(int next, long weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }

    }

    static HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        for(int i= 1;i<=N;i++) {
            map.put(i, new PriorityQueue<>());
        }

        for(int i= 0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, cost));
            map.get(b).add(new Node(a, cost));

        }

        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(distance[now.next]<now.weight) {
                continue;
            }
            distance[now.next] = now.weight;

            for (Node node : map.get(now.next)) {
                if (distance[node.next] > now.weight + node.weight) {
                    pq.add(new Node(node.next, now.weight + node.weight));
                }
            }
        }



        long[] reverseDistance = new long[N + 1];
        Arrays.fill(reverseDistance, Long.MAX_VALUE);
        pq = new PriorityQueue<>();
        pq.add(new Node(N, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(reverseDistance[now.next]<now.weight) {
                continue;
            }
            reverseDistance[now.next] = now.weight;

            for (Node node : map.get(now.next)) {
                if (reverseDistance[node.next] > now.weight + node.weight) {
                    pq.add(new Node(node.next, now.weight + node.weight));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        long[] betweenDistance = new long[N + 1];

        Arrays.fill(betweenDistance, Long.MAX_VALUE);
        pq = new PriorityQueue<>();
        pq.add(new Node(a, 0));
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(betweenDistance[now.next]<now.weight) {
                continue;
            }
            betweenDistance[now.next] = now.weight;

            for (Node node : map.get(now.next)) {
                if (betweenDistance[node.next] > now.weight + node.weight) {
                    pq.add(new Node(node.next, now.weight + node.weight));
                }
            }
        }

        if (betweenDistance[b] == Long.MAX_VALUE) {
            System.out.println(-1);
            return ;
        }


        long cheapest = Math.min(distance[a] + reverseDistance[b], distance[b] + reverseDistance[a]);

        if (cheapest == Long.MAX_VALUE||cheapest<0) {
            System.out.println(-1);
            return ;
        }



        System.out.println(betweenDistance[b]+cheapest);
    }
}
