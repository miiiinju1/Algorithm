import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class CanEdge implements Comparable<CanEdge> {
        int a,b, cost, nodeCost;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("CanEdge{");
            sb.append("a=").append(a);
            sb.append(", b=").append(b);
            sb.append(", cost=").append(cost);
            sb.append(", nodeCost=").append(nodeCost);
            sb.append('}');
            return sb.toString();
        }

        public CanEdge(int a, int b, int cost, int nodeCost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
            this.nodeCost = nodeCost;
        }


        @Override
        public int compareTo(CanEdge o) {
//            if (this.cost == o.cost) {
                return Integer.compare(this.nodeCost, o.nodeCost);
//            }
//            return Integer.compare(this.cost, o.cost);
        }
    }
    static int[] parent;

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
            parent[fa] = fb;
            return true;
        }
        return false;
    }

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static Map<Integer, List<Edge>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        int min = Integer.MAX_VALUE;
        int[] nodeCost = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
            parent[i] = i;
            nodeCost[i] = Integer.parseInt(br.readLine());
            min = Math.min(min, nodeCost[i]);
        }

        PriorityQueue<CanEdge> pq = new PriorityQueue<>();

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int nc = cost*2 + nodeCost[a] + nodeCost[b];
            pq.add(new CanEdge(a, b, cost, nc));
        }

        long sum = 0L;
//        System.out.println("pq = " + pq);
        while(!pq.isEmpty()) {
            CanEdge now = pq.poll();
            if (union(now.a, now.b)) {
                sum += (now.cost * 2);
//                System.out.println("now.cost*2 = " + now.cost*2);
                map.get(now.a).add(new Edge(now.b, now.cost));
                map.get(now.b).add(new Edge(now.a, now.cost));

            }
        }

        // 이제 트리 구조인데
        for(int i = 1;i<=N;i++) {
//            System.out.println("((long)map.get(i).size()*nodeCost[i]) = " + ((long)map.get(i).size()*nodeCost[i]));
            sum += ((long)map.get(i).size() * nodeCost[i]);
        }

        sum += min;

        System.out.println(sum);






    }
}
