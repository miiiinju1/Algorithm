
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
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

    static class Friend implements Comparable<Friend> {
        int num, cost;

        public Friend(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Friend o) {
            return Integer.compare(this.cost, o.cost);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        PriorityQueue<Friend> pq = new PriorityQueue<>(N);

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            pq.add(new Friend(i, Integer.parseInt(st.nextToken())));
        }

        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());


            union(v, w);
        }


        int sum = 0;

        while(!pq.isEmpty()) {
            final Friend friend = pq.poll();


            if (union(friend.num, 0)) {
                sum += friend.cost;
            }

        }

        if(sum> k) {
            System.out.println("Oh no");
            return;
        }
        System.out.println(sum);




    }
}
