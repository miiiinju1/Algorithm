import java.io.*;
import java.util.*;

public class Main {
    static class Next implements Comparable<Next> {
        int index, weight;

        public Next(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Next o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static HashMap<Integer, PriorityQueue<Next>> map;

    static int[] parent;


    static String search(int start) {
        StringBuilder sb= new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(start);
        while(parent[start]!=start) {
            start = parent[start];
            list.add(start);
        }

        for(int i= list.size()-1;i>=0;i--) {
            sb.append(list.get(i)).append(" ");
        }
        return sb.toString();

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            map = new HashMap<>();

            for(int i= 0;i<M;i++) {
                map.put(i, new PriorityQueue<>());
            }
            for(int i= 0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                map.get(x).add(new Next(y, z));
                map.get(y).add(new Next(x, z));
            }

            int[] visited = new int[M];
            parent = new int[M];
            Arrays.fill(visited, Integer.MAX_VALUE);
            PriorityQueue<Next> pq = new PriorityQueue<>();
            pq.add(new Next(0, 0));

            visited[0] = 0;
            while(!pq.isEmpty()) {
                final Next now = pq.poll();

                for (Next next : map.get(now.index)) {
                    if(visited[next.index]>next.weight+visited[now.index]) {
                        parent[next.index] = now.index;
                        visited[next.index] = next.weight+visited[now.index];
                        pq.add(next);
                    }
                }
            }

            if(visited[M-1]==Integer.MAX_VALUE) {
                bw.write("Case #"+(tc)+": -1\n");
            }
            else {
                bw.write("Case #"+tc+": "+search(M-1)+"\n");
            }

        }
        bw.flush();bw.close();
    }
}
