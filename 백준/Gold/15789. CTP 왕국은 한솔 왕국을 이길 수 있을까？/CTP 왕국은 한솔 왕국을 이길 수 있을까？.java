
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] parent, size;
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
        if(fa!=fb) {
            parent[fa] = fb;
            size[fb] += size[fa];
            size[fa] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];
        Arrays.fill(size, 1);
        for(int i= 1;i<=N;i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);
        }
        st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int cHead = find(C);
        int hHead = find(H);

        for (int k = 0; k < K; k++) {

            HashSet<Integer> visited = new HashSet<>();

            int maxSize = -1;
            int maxHead = -1;
            for (int i = 0; i < N; i++) {
                int head = find(i);
                if(head == cHead) continue;
                if(visited.contains(head) || head == hHead) continue;

                visited.add(head);
                int s = size[head];

                if(s > maxSize) {
                    maxSize = s;
                    maxHead = head;
                }
            }

            if(maxHead!=-1)
                union(maxHead, cHead);
        }

        System.out.println(size[cHead]);

    }

}
