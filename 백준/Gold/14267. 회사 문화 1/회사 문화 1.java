import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int[] compliment;
    static int[] result;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    static void dfs(int now, int value) {
        visited[now] = true;
        result[now]+=(value+compliment[now]);

        for (Integer next : map.get(now)) {
            dfs(next, value+compliment[now]);
        }
        compliment[now] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] parent = new int[n+1];

        for(int i = 1;i<=n;i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }
        compliment = new int[n+1];
        result = new int[n+1];
        for(int i =0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            compliment[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            // 꼭 양방향 안 해도 될 것 같은데?
            if(parent[i]!=-1)
            map.get(parent[i]).add(i);
        }
        visited = new boolean[n+1];
        for(int i = 1;i<=n;i++) {
            if(!visited[i]) {
                dfs(i,0);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i= 1;i<=n;i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);


    }
}
