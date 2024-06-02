
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    static int[] parent;
    static int[] size;

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

        if (fa != fb) {
            parent[fa] = fb;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N * M];
        size = new int[N * M];

        Map<Character, Integer> dir = new HashMap<>();

        dir.put('N', 0);
        dir.put('W', 1);
        dir.put('E', 2);
        dir.put('S', 3);
        for(int i= 0;i<N*M;i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            final String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = dir.get(str.charAt(j));
            }
        }

        for(int i= 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
                int d = map[i][j];
                int y = dy[d] + i;
                int x = dx[d] + j;
                union((i) * M + j, (y) * M + x);
            }
        }
        Set<Integer> result = new HashSet<>();
        for(int i = 0;i<N*M;i++) {
            result.add(find(parent[i]));
        }

//        System.out.println(result);
        System.out.println(result.size());






    }
}
