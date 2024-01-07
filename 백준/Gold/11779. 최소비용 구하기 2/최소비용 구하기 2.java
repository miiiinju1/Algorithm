import java.io.*;
import java.util.*;

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

    static int[] before;
    static HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(br.readLine());

        for(int i= 1;i<=N;i++) {
            map.put(i, new PriorityQueue<>());
        }

        for(int i= 0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, cost));

        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
         before = new int[N+1];
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(distance[now.next]<now.weight) {
                continue;
            }
            distance[now.next] = now.weight;

            for (Node node : map.get(now.next)) {
                if (distance[node.next] > now.weight + node.weight) {
                    distance[node.next] = now.weight+node.weight;
                    before[node.next] = now.next;
                    pq.add(new Node(node.next, now.weight + node.weight));
                }
            }
        }


        System.out.println(distance[end]);
        StringBuilder sb = new StringBuilder();
        Stack<Integer> path = new Stack<>();
        for(int at = end; at != start; at = before[at]) {
            path.push(at);
        }
        path.push(start);

        int count = 0;
        while (!path.isEmpty()) {
            count++;
            sb.append(path.pop() + " ");
        }
        System.out.println(count);
        System.out.println(sb);

    }
}

