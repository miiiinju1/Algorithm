import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int next, weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight-o.weight;
        }
    }

    static HashMap<Integer, ArrayList<Node>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        char[] sex = new char[N + 1];
        for (int i = 1; i <= N; i++) {
            sex[i] = st.nextToken().charAt(0);
            map.put(i, new ArrayList<>());
        }
        for(int i= 1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (sex[u] != sex[v]) {
                map.get(u).add(new Node(v, d));
                map.get(v).add(new Node(u, d));
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        visited[0] = true;
        long sum = 0;
        int count = 0;
        q.add(new Node(1, 0));
        while (!q.isEmpty()) {
            Node now = q.poll();
            if(visited[now.next])
                continue;
            count++;
            visited[now.next] = true;
            sum += now.weight;
            ArrayList<Node> list = map.get(now.next);

            for (Node node : list) {
                if(!visited[node.next])
                    q.add(node);
            }



        }

        if(count!=N)
            System.out.println(-1);
        else
            System.out.println(sum);
    }
}
