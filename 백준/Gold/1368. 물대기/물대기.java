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

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("a=").append(a);
            sb.append(", b=").append(b);
            sb.append(", weight=").append(weight);
            sb.append('}');
            return sb.toString();
        }

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
        int[] weight = new int[N];
        for(int i= 1;i<=N;i++) {
            parent[i] = i;
        }
        for(int i= 1;i<=N;i++) {
//            weight[i] = Integer.parseInt(br.readLine());
            pq.add(new Node(0, i, Integer.parseInt(br.readLine())));
        }
        int[][] map = new int[N][N];

        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 0;j<N;j++) {

//                map[i][j] = Integer.parseInt(st.nextToken());
                int temp = Integer.parseInt(st.nextToken());
                if(i<j) {
                    pq.add(new Node(i+1, j+1, temp));
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
