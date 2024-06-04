
import java.io.*;
import java.util.*;

public class Main {
    static class Phase {
        int now;
        int depth;
        public Phase(int now, int depth) {
            this.now = now;
            this.depth = depth;
        }

    }
    static Map<Integer, List<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        for(int i = 1;i<=N;i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.get(a).add(b);
            map.get(b).add(a);
        }

        boolean[][] visited = new boolean[Y+1][N + 1];
        Deque<Phase> q = new ArrayDeque<>();
        q.add(new Phase(X, 0));
//
        visited[0][X] = true;

        PriorityQueue<Integer> result = new PriorityQueue<>();
        while(!q.isEmpty()) {
            final Phase now = q.poll();

            if(now.depth==Y) {
                result.add(now.now);
                continue;
            }
            for (Integer next : map.get(now.now)) {

                if(!visited[now.depth+1][next]) {
                    visited[now.depth + 1][next] = true;
                    q.add(new Phase(next, now.depth + 1));
                }
            }
        }
        if(result.isEmpty()) {
            System.out.println(-1);
            return;
        }

        while(!result.isEmpty()) {
            bw.write(result.poll() + " ");
        }
        bw.flush();bw.close();



    }
}
