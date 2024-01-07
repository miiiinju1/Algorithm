import java.io.*;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            map.put(i, new PriorityQueue<>());
        }
        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, cost));

        }

        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (distance[now.next] < now.weight) {
                continue;
            }
            distance[now.next] = now.weight;

            for (Node node : map.get(now.next)) {
                if (distance[node.next] > now.weight + node.weight) {
                    distance[node.next] = now.weight+node.weight;
                    pq.add(new Node(node.next, now.weight + node.weight));
                }
            }
        }

        for (int i= 1;i<=N;i++) {
            if(distance[i]==Long.MAX_VALUE) {
                bw.write("INF\n");
            }else
            bw.write(distance[i]+"\n");
        }
bw.flush();bw.close();
    }
}
