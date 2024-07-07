
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//dp 풀이

public class Main {
    static class Edge {
        int to, cost, time;

        public Edge(int to, int cost, int time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    static Map<Integer, ArrayList<Edge>> map = new HashMap<>();

    static int[][] dp;

    static int dfs(int now, int cost) {
        if(cost > M) {
            return Integer.MAX_VALUE;
        }
        if (now == N) {
            dp[now][cost] = 0;
            return 0;
        }
        if (dp[now][cost] != -1) {
            return dp[now][cost];
        }
        int minTime = Integer.MAX_VALUE;
        for (Edge edge : map.get(now)) {
            int nextCost = edge.cost + cost;
            if(nextCost <= M) {
                int nextTime = dfs(edge.to, nextCost);
                if (nextTime != Integer.MAX_VALUE) {
                    minTime = Math.min(minTime, nextTime + edge.time);
                }
            }
        }
        dp[now][cost] = minTime;
        return minTime;
    }


    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(c<=M)
                map.get(u).add(new Edge(v, c, d));
        }



        int ans = dfs(1, 0);
        if(ans==Integer.MAX_VALUE) {
            System.out.println("Poor KCM");
            return;
        }
        System.out.println(ans);



    }
}
