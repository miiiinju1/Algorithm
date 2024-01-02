import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa>fb) {
            parent[fb] = fa;
        }
        else {
            parent[fa] = fb;
        }
    }
    static int find (int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        HashSet<Integer> know = new HashSet<>();
        int knowNumbers = Integer.parseInt(st.nextToken());
        for (int i = 0; i < knowNumbers; i++) {
            know.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            int participants = Integer.parseInt(st.nextToken());
            int a= -1;
            a = Integer.parseInt(st.nextToken());
            list.add(a);

            for (int j = 1; j < participants; j++) {
                int temp = Integer.parseInt(st.nextToken());
                union(a, temp);
            }
        }

        know = know.stream().map(i -> find(i)).collect(Collectors.toCollection(HashSet::new));

        int sum = 0;
        for (int i = 0; i < M; i++) {
            if (!know.contains(find(list.get(i)))) {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
