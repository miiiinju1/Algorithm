import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    static class Node implements Comparable<Node> {
        int a, b, c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return o.c - this.c;
        }
    }

    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            if(a>b) {
                int temp = fa;
                fa = fb;
                fb = temp;
            }
            parent[fa] = fb;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for(int i= 1;i<=N;i++) {
            parent[i] = i;
        }
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int m = 0;m<M;m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pq.add(new Node(A, B, C));
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            union(node.a, node.b);
            if(find(A)==find(B)) {
                System.out.println(node.c);
                return;
            }

        }
        
        

    }
}
