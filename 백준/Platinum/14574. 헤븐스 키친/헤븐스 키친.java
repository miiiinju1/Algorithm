import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Chef  {

        int p,c;
        int index;
        public Chef(int p, int c, int index) {
            this.p = p;
            this.c = c;
            this.index = index;
        }

//        @Override
//        public int compareTo(Chef o) {
////            if(this.p == o.p) {
////                return Integer.compare(o.c, this.c);
////            }
////            return Integer.compare(this.p, o.p);
//            return Double.compare((double) o.c / o.p, (double) this.c / this.p);
//        }
    }
    static class Node {
        int diff, i1, i2;

        public Node(int diff, int i1, int i2) {
            this.diff = diff;
            this.i1 = i1;
            this.i2 = i2;
        }
    }

    static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.diff, o1.diff));

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            parent[fa] = fb;
            return true;
        }
        return false;
    }
    static int[] parent;

    static int find(int v) {
        if(parent[v] == v)
            return v;

        parent[v] = find(parent[v]);
        return parent[v];

    }
    static boolean[] visited;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

//        PriorityQueue<Chef> pq = new PriorityQueue<>();
        Chef[] chefs = new Chef[N];
        for (int i = 0; i <N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            chefs[i] = new Chef(p, c, i + 1);

        }
        parent = new int[N+1];
        for(int i= 1;i<=N;i++) parent[i] = i;

        for(int i= 0;i<N;i++) {
            for(int j = i+1;j<N;j++) {
                pq.add(new Node((chefs[i].c + chefs[j].c) / (Math.abs(chefs[i].p - chefs[j].p)), i+1, j+1));
            }
        }
        long sum = 0;


        visited = new boolean[N + 1];

        while(!pq.isEmpty()) {
            final Node poll = pq.poll();

            if(union(poll.i1, poll.i2)){
                sum += poll.diff;
                map.computeIfAbsent(poll.i1, v -> new ArrayList<>());
                map.computeIfAbsent(poll.i2, v -> new ArrayList<>());

                map.get(poll.i1).add(poll.i2);
                map.get(poll.i2).add(poll.i1);


            }

        }
        StringBuilder sb = new StringBuilder();

        visited[1] = true;
        dfs(1, sb);

//        while(!pq.isEmpty()) {
//
//            Chef chef1 = pq.poll();
//
//            if(pq.isEmpty()) {
//                break;
//            }
//            Chef chef2 = pq.peek();
//
//            sum += (chef1.c + chef2.c) / (Math.abs(chef1.p - chef2.p));
//
//            sb.append(chef2.index).append(" ").append(chef1.index).append("\n");
//
//
//        }
        System.out.println(sum);
        System.out.println(sb);

    }

    static void dfs(int now, StringBuilder sb) {

        for (Integer next : map.get(now)) {
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, sb);
                sb.append(now).append(" ").append(next).append("\n");
            }
        }

    }


}
