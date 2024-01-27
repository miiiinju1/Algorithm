import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int x,y,weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] parent;
    static int find(int v) {
        if(parent[v] == v ) {
            return v;
        }

        parent[v] = find(parent[v]);
        return parent[v];
    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            parent[fa] = fb;
            return true;

        }
        else {
            return false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }

            long max = 0;
            parent = new int[m];
            for(int i= 0;i<m;i++) {
                parent[i] = i;
            }
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for(int i= 0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                max+=z;
                pq.add(new Node(x, y, z));
            }
            long sum = 0;

            while(!pq.isEmpty()) {
                final Node now = pq.poll();
                if(union(now.y, now.x)) {
                    sum += now.weight;
                }
            }

            bw.write(max-sum + "\n");

        }
        bw.flush();bw.close();

    }
}
