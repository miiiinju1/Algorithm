
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


    static int[] parent;
    static int[] isLinked;
    static class Edge {
        int a, b,cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa!=fb) {
            if (isLinked[fa]==0 && isLinked[fb]==0) {
                parent[fa] = fb;
                return true;
            }
            if (isLinked[fa] == 1 ^ isLinked[fb] == 1) {
                parent[fa] = fb;
                isLinked[fa] = 1;
                isLinked[fb] = 1;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        isLinked = new int[N + 1];
        List<Edge> edges = new ArrayList<>();

        for(int i =1;i<=N;++i) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        int[] plants = new int[K];

        for (int i = 0; i < K; ++i) {
            plants[i] = Integer.parseInt(st.nextToken());
            isLinked[plants[i]] = 1;
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new Edge(u, v, w));

        }
        Collections.sort(edges, Comparator.comparingInt(o -> o.cost));

        int sum = 0;
        for (Edge edge : edges) {
            if (union(edge.a, edge.b)) {
                sum += edge.cost;
            }
        }
        System.out.println(sum);
    }

}
