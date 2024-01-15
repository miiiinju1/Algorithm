import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Phase {
        int now;

        public Phase(int now, int count) {
            this.now = now;
            this.count = count;
        }

        int count;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Phase{");
            sb.append("now=").append(now);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }
    }
    static HashMap<Integer, ArrayList<Integer>> route = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> station = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        for(int i= 1;i<=N;i++) {
            station.put(i, new ArrayList<>());
        }
        for(int i= 1;i<=L;i++) {
            route.put(i, new ArrayList<>());
        }
        boolean[] routeVisited = new boolean[L + 1];
        boolean[] stationVisited = new boolean[N + 1];
        for(int i= 1;i<=L;i++) {
            st = new StringTokenizer(br.readLine());

            ArrayList<Integer> stations = route.get(i);
            while(st.hasMoreTokens()) {
                int input = Integer.parseInt(st.nextToken());
                if (input==-1) {
                    break;
                }
                else {
                    stations.add(input);
                    station.get(input).add(i);
                }
            }

        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        ArrayDeque<Phase> q = new ArrayDeque<>();
        for (Integer i : station.get(start)) {
            routeVisited[i] = true;
            for (Integer integer : route.get(i)) {
                if(!stationVisited[integer]) {
                    stationVisited[integer] = true;
                    if(integer==dest) {
                        System.out.println(0);
                        return ;
                    }
                    q.add(new Phase(integer, 0));
                }
            }

        }
        stationVisited[start] = true;
        while(!q.isEmpty()) {
            final Phase now = q.poll();

            if(now.now==dest) {
                System.out.println(now.count);
                return ;
            }
            for (Integer i : station.get(now.now)) {
                if(!routeVisited[i]) {
                    routeVisited[i] = true;
                    for (Integer integer : route.get(i)) {
                        if(!stationVisited[integer]) {
                            stationVisited[integer] = true;
                            q.add(new Phase(integer, now.count + 1));
                        }
                    }
                }


            }




        }

        System.out.println(-1);

    }
}

