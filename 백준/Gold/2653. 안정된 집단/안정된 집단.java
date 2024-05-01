import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int find(int v) {
        if(parent[v] == v)
            return v;

        parent[v] = find(parent[v]);
        return parent[v];
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            parent[fa] = fb;
        }
    }

    static boolean isStable(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        return fa==fb;
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[N];
        HashMap<Integer, Set<Integer>> check = new HashMap<>();

        for(int i = 0;i<N;i++) {
            parent[i] = i;
            check.put(i, new HashSet<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int j= 0;j<N;j++) {
            int b = Integer.parseInt(st.nextToken());
            if(b==0) {
                union(0, j);
            }
            else {
                check.get(0).add(j);
                check.get(j).add(0);
            }

        }
        for(int i = 1;i<N;i++) {
             st = new StringTokenizer(br.readLine());
            for(int j= 0;j<N;j++) {
                final int b = Integer.parseInt(st.nextToken());
                if(b==0) {
                    if (check.get(j).contains(i)) {
                        System.out.println(0);
                        return;
                    }
                    union(i, j);
                }
                else {
                    check.get(i).add(j);
                    check.get(j).add(i);
                    if (isStable(i, j)) {
                        System.out.println(0);
                        return ;
                    }
                }
            }
        }
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for(int i= 0;i<N;i++) {
            int t = parent[i];
            map.computeIfAbsent(t, k -> new PriorityQueue<>());
            map.get(t).add(i + 1);
        }
        StringBuilder sb = new StringBuilder();
        final List<PriorityQueue<Integer>> collect = map.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue().peek()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        sb.append(map.size()).append("\n");

        for (PriorityQueue<Integer> pq : collect) {
            if(pq.size()<=1) {
                System.out.println(0);
                return ;
            }
            while(!pq.isEmpty()) {
                final Integer i = pq.poll();
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
