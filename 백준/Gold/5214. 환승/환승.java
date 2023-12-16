import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] hypertube ;
    static HashMap<Integer, ArrayList<Integer>> station = new HashMap<>();

    static int N,K,M;
    static class Point{
        int now, depth;

        public Point(int now, int depth) {
            this.now = now;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i= 1;i<=N;i++) {
            station.put(i, new ArrayList<>());
        }
        hypertube = new int[M][K];
        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int index = 0;
            while(st.hasMoreTokens()) {
                int s = Integer.parseInt(st.nextToken());
                hypertube[i][index++] = s;
                station.get(s).add(i);
            }
        }
        boolean[] hyperVisited = new boolean[M];
        boolean[] visited = new boolean[N+1];
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(1, 1));
        while(!q.isEmpty()) {
            final Point now = q.poll();

            if(visited[now.now])
                continue;
            visited[now.now] = true;
            if(now.now==N) {
                System.out.println(now.depth);
                return ;
            }
            for (Integer i : station.get(now.now)) {
                for (Integer integer : hypertube[i]) {
                    if(!hyperVisited[i]&&!visited[integer]) {
                        q.add(new Point(integer, now.depth + 1));

                    }
                }hyperVisited[i]= true;
            }
        }
        System.out.println(-1);

    }
}
