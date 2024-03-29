import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    static int index=0;
    static int[] order;

    static boolean[] visited;
    static void dfs(int now) {

        final HashSet<Integer> integers = map.get(now);

        for(int i = 0;i<integers.size();i++) {
            if (map.get(now).contains(order[index])) {
                if (!visited[order[index]]) {
                    visited[order[index]] = true;
                    dfs(order[index++]);
                }

            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        visited = new boolean[N + 1];
        order = new int[N + 1];
        for(int i = 1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(a, k -> new HashSet<>());
            map.get(a).add(b);

            map.computeIfAbsent(b, k -> new HashSet<>());
            map.get(b).add(a);
        }

        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
        visited[1] = true;
        index++;
        dfs(1);
        visited[0] = true;
        for(int i = 1;i<=N;i++) {
            if(!visited[i]) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);

    }
}
