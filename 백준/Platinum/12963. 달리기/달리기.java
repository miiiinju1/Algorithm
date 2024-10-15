
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1_000_000_007;
    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        parent[v]= find(parent[v]);
        return parent[v];
    }
    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        int start = find(0);
        int end = find(N - 1);

        // start랑 end는 항상 다른데

        if (fa == fb) {
            return false;
        }

        // 둘 다 각각 따로면 연결하면 안 됨
        if (fa == start && fb == end) {
            return true;
        } else if (fa == end && fb == start) {
            return true;
        }
        parent[fa] = fb;
        return false;
    }


    static int[] parent;

    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        List<int[]> bridges = new ArrayList<>(M);
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            bridges.add(
                new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});

        }
        parent = new int[N];

        for (int i = 0; i < N; ++i) {
            parent[i] = i;
        }

        int[] values = new int[M];
        if (M != 0) {
            values[0] = 1;
        }
        for (int i = 1; i < M; ++i) {
            values[i] = (int) ( ((long) values[i - 1] * 3) % MOD);
        }

//        System.out.println(Arrays.toString(values));
        int result = 0;
        for (int i = M - 1; i >= 0; --i) {
            int[] bridge = bridges.get(i);
            if (union(bridge[0], bridge[1])) {
                result = (result + values[i]) % MOD;
            }
        }

        System.out.println(result);





    }

}
//6 36
//1 3
//1 2
//2 3
//0 1
//4 5
//3 5
//0 2
//1 4
//4 3
//1 3
//1 2
//2 3
//0 1
//4 5
//3 5
//0 2
//1 4
//4 3
//1 3
//1 2
//2 3
//0 1
//4 5
//3 5
//0 2
//1 4
//4 3
//1 3
//1 2
//2 3
//0 1
//4 5
//3 5
//0 2
//1 4
//4 3