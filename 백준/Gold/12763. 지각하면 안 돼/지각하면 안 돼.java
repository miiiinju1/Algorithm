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
        long time, cost;

        public Node(int next, long time, long cost) {
            this.next = next;
            this.time = time;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost==o.cost){
                return Long.compare(this.time, o.time);
            }
            return Long.compare(this.cost,o.cost);
        }
    }
    static HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i= 1;i<=N;i++) {
            map.put(i, new PriorityQueue<>());
        }

        int L = Integer.parseInt(br.readLine());
        for(int i= 0;i<L;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map.get(start).add(new Node(end, time, cost));
            map.get(end).add(new Node(start, time, cost));
        }
        long[] visitCost = new long[N + 1];
        long[] timeCost = new long[N + 1];
        Arrays.fill(visitCost, Long.MAX_VALUE);
        Arrays.fill(timeCost, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));

        while (!pq.isEmpty()) {
             Node now = pq.poll();

            if (visitCost[now.next] < now.cost && timeCost[now.next] < now.time) {
                continue;
            }

             visitCost[now.next] = now.cost;
             timeCost[now.next] = now.time;

             if(now.next==N) {
                 if(now.time<=T) {
                     System.out.println(now.cost);
                     return ;
                 }
             }
            for (Node node : map.get(now.next)) {
                if((timeCost[node.next]>now.time+node.time||visitCost[node.next]>now.cost+node.cost)&&now.cost+node.cost<=M) {
                    pq.add(new Node(node.next, node.time + now.time, node.cost + now.cost));
                }
            }
        }

        System.out.println(-1);

    }
}
