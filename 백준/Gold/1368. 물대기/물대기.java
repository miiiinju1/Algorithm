import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            parent[fa]= fb;
            return true;
        }
        else {
            return false;
        }
    }
    static int[] parent;

    static class Node implements Comparable<Node> {
        int a, b,weight;

        public Node (int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.weight =w ;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        parent = new int[N + 1];

        for(int i= 1;i<=N;i++) {
            parent[i] = i;
            pq.add(new Node(0, i, Integer.parseInt(br.readLine())));
        }

        for(int i= 1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 1;j<=N;j++) {
                if(i<j) {
                    pq.add(new Node(i, j, Integer.parseInt(st.nextToken())));
                }
                else {
                    st.nextToken();
                }
            }
        }
        long sum = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(union(node.a,node.b)) {
                sum += node.weight;
            }
        }
        System.out.println(sum);

    }
}
