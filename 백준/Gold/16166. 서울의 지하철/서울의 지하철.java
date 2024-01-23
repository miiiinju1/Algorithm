import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Route {
        int num, depth;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Route{");
            sb.append("num=").append(num);
            sb.append(", depth=").append(depth);
            sb.append('}');
            return sb.toString();
        }

        public Route(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
    static HashMap<Integer, HashSet<Long>> route  = new HashMap<>();

    static HashMap<Long, ArrayList<Integer>> station = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for(int i= 1;i<=N;i++) {
            route.put(i, new HashSet<>());
        }

        for(int i= 1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());

            int z = Integer.parseInt(st.nextToken());
            for(int j= 0;j<z;j++) {
                long s = Long.parseLong(st.nextToken());

                route.get(i).add(s);

                if(!station.containsKey(s)) {
                    station.put(s, new ArrayList<>());
                }

                station.get(s).add(i);
            }
        }
        long dest = Long.parseLong(br.readLine());


        boolean[] visited = new boolean[N + 1];

        ArrayDeque<Route> q = new ArrayDeque<>();

        for (Integer i : station.get(0L)) {
            q.add(new Route(i, 0));
            visited[i] = true;
        }

        while(!q.isEmpty()) {
            final Route now = q.poll();
            if (route.get(now.num).contains(dest)) {
                System.out.println(now.depth);
                return ;
            }

            //역에 포함된 도시
            for (Long l : route.get(now.num)) {
                //도시에 포함된 역
                for (Integer i : station.get(l)) {
                    if (!visited[i]) {
                        q.add(new Route(i, now.depth + 1));
                        visited[i] = true;
                    }
                }
            }


        }
        System.out.println(-1);

    }
}
