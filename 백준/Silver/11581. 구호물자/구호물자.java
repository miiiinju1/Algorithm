import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static boolean[] visited;
    static boolean search(int now) {
        if(now == N) {
            return true;
        }
        if(!visited[now]) {
            visited[now] = true;
            for (Integer i : map.get(now)) {
                if(!search(i)) {
                    return false;
                }
            }
            visited[now] = false;
        }
        else {
            return false;
        }
        return true;
    }
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        for(int i = 1;i<N;i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 1; i < N; i++) {
            int count = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            final ArrayList<Integer> list = map.get(i);
            for (int j = 0; j < count; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }
        if (search(1)) {
            System.out.println("NO CYCLE");

        }
        else {
            System.out.println("CYCLE");

        }
    }
}
