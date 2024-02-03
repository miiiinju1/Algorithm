import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Warp implements Comparable<Warp>{
        int a,b,time;

        public Warp(int a, int b, int time) {
            this.a = a;
            this.b = b;
            this.time = time;
        }

        @Override
        public int compareTo(Warp o) {
            return Integer.compare(this.time, o.time);
        }
    }
    static int[] exit;
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
        else {
            return false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i= 1;i<=N;i++) {
            parent[i] = i;
        }
        PriorityQueue<Warp> pq = new PriorityQueue<>();
        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Warp(Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())));
        }

//        exit = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1;i<=N;i++) {
            pq.add(new Warp(0, i, Integer.parseInt(st.nextToken())));
        }


        int result = 0;
        while(!pq.isEmpty()) {
            final Warp now = pq.poll();

            if(union(now.a,now.b))
                result+=now.time;

        }
        System.out.println(result);
    }

}

//
//    |3   |1  |1
//    1  - 2 - 3
//       2   1


