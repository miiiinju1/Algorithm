
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    static Map<Integer, List<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Map<Integer, List<Set<Integer>>> recipe = new HashMap<>();
        for(int i= 1;i<=N;i++) {
            map.put(i, new ArrayList<>());
            recipe.put(i, new ArrayList<>());
        }
//        int[] in = new int[N + 1];

        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                int n = Integer.parseInt(st.nextToken());
                temp.add(n);
            }
            int target = Integer.parseInt(st.nextToken());

            recipe.get(target).add(new HashSet<>(temp));
            for (Integer from : temp) {
                map.get(from).add(target);
            }
        }

        int L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        boolean[] visited = new boolean[N + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < L; i++) {
            int temp = Integer.parseInt(st.nextToken());

            visited[temp] = true;
            q.add(temp);
        }

        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()) {
            Integer poll = q.poll();

            result.add(poll);
            for (Integer next : map.get(poll)) {
                if(visited[next]) continue;

                for (Set<Integer> set : recipe.get(next)) {
                    if(set.contains(poll)) {
                        set.remove(poll);
                    }

                    if(set.isEmpty()) {
                        visited[next] = true;
                        q.add(next);
                        break;
                    }
                }
            }

        }
        System.out.println(result.size());
        Collections.sort(result);
        System.out.println(result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
//        System.out.println("result = " + result);
    }
}
