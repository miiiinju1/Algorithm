import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int next, width;
        int depth;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("next=").append(next);
            sb.append(", width=").append(width);
            sb.append('}');
            return sb.toString();
        }

        public Node(int next, int width) {
            this.next = next;
            this.width = width;
        }

        public Node(int next, int width, int depth) {
            this.next = next;
            this.width = width;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.width, this.width);
        }
    }
    static HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        for(int i= 0;i<p;i++) {
            map.put(i, new PriorityQueue<>());
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, width));
            map.get(b).add(new Node(a, width));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, Integer.MAX_VALUE, 0));

        int[] visited = new int[p];

        Arrays.fill(visited, Integer.MAX_VALUE);
        while(!pq.isEmpty()) {
            final Node now = pq.poll();
            if (visited[now.next] > now.depth  ) {
                visited[now.next] = now.depth ;
            }
            else {
                continue;
            }

            if(now.next==end) {
                System.out.println(now.width);
                return ;
            }

            for (Node node : map.get(now.next)) {

                pq.add(new Node(node.next, Math.min(now.width, node.width), now.depth + 1));

            }

        }

    }
}
