import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] schedule;
    static boolean[] visited;
    static void search(int now) {
        if(visited[now])
            return;

        visited[now] = true;
        for (Integer next : map.get(now)) {
            if(!visited[next]) {
                search(next);
            }
        }
    }
    static int N,M;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        schedule = new int[M];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int n = 1;n<=N;n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1;i<=N;i++) {
                if (st.nextToken().equals("1")) {
                    map.get(n).add(i);
                    map.get(i).add(n);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());


        boolean possible = true;
        for(int m = 0;m<M;m++) {
            schedule[m] = Integer.parseInt(st.nextToken());

        }
        search(schedule[0]);
        for(int m = 0;m<M;m++) {
            if (!visited[schedule[m]]) {
                possible = false;
            }
        }
        if(possible) {
            System.out.println("YES");

        }
        else {
            System.out.println("NO");
        }
    }
}
