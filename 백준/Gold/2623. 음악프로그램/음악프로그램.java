import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Set<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Deque<Integer> q = new ArrayDeque<>();

        for(int i= 1;i<=N;i++) {
            map.put(i, new HashSet<>());
        }
        int[] in = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());

            for (int j = 1; j < n; j++) {
                int to = Integer.parseInt(st.nextToken());
                if(!map.get(before).contains(to)) {
                    map.get(before).add(to);
                    in[to]++;
                }
                before = to;
            }
        }

        for(int i= 1;i<=N;i++) {
            if(in[i]==0) {
                q.add(i);
                visited[i] = true;
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()) {
            Integer poll = q.poll();
            result.add(poll);

            for (Integer next : map.get(poll)) {
                if(visited[next]) {
                    System.out.println(0);
                    return;
                }
                in[next]--;
                if(in[next]==0) {
                    visited[next] = true;
                    q.add(next);
                }
            }

        }


//        for(int i =1;i<=N;i++) {
//            if(!visited[i] && in[i] == 0) {
//                result.add(i);
//            }
//        }
        if(result.size()!=N) {
            System.out.println(0);
            return;
        }
        for (Integer i : result) {
            bw.write(i + "\n");
        }
        bw.flush();bw.close();
//        System.out.println("map = " + map);
//            System.out.println(result);

    }
}
