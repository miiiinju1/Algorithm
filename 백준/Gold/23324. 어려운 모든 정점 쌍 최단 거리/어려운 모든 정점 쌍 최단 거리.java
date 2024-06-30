
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

//    static Map<Integer, List<Edge>> map = new HashMap<>();
//    static class Edge {
//        int to, cost;
//
//        public Edge(int to, int cost) {
//            this.to = to;
//            this.cost = cost;
//        }
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i= 1;i<=N;i++) {
            parent[i] = i;
//            map.put(i, new ArrayList<>());
        }

        for(int i= 1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

//            int cost = i == K ? 1 : 0;
            if(i!=K) {
                union(u, v);
//            map.get(u).add(new Edge(v, cost));
//            map.get(v).add(new Edge(u, cost));

            }
        }

        HashMap<Integer, Integer> count = new HashMap<>();

        for(int i = 1;i<=N;i++) {
            final int find = find(i);
            count.put(find, count.getOrDefault(find, 0) + 1);
        }

        if(count.size()==1) {
            System.out.println(0);
            return;
        }
        long sum = 1;

        for (Integer value : count.values()) {
            sum*=value;
        }
        System.out.println(sum);


//        int[] distance = new int[N + 1];

//




    }

    static int[] parent;
    static int find(int v) {
        if(parent[v]==v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa!=fb) {
            parent[fa] = fb;
        }
    }
}
