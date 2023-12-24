import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int find(int v) {
        if(parent[v] ==v ) return v;
        parent[v] = find(parent[v]);
        return parent[v];
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            if(size[fa]>size[fb]) {
                int temp = fa;
                fa = fb;
                fb = temp;
            }

            parent[fa] = parent[fb];
            size[fb] += size[fa];
        }

    }
    static int[] parent;
    static int[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i = 1;i<=N;i++) {
            parent[i] = i;
        }
        size = new int[N+1];
        int M = Integer.parseInt(br.readLine());

        for(int n = 1;n<=N;n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1;i<=N;i++) {
                if (st.nextToken().equals("1")) {
                    union(n, i);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int temp = find(Integer.parseInt(st.nextToken()));
        for (int m = 1; m < M; m++) {
            int schedule = Integer.parseInt(st.nextToken());

            int p = find(schedule);
            if(temp!=p) {
                System.out.println("NO");
                return ;
            }


        }
        System.out.println("YES");
    }
}
