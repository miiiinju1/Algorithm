
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] friend ;
    static int findFriend(int v) {
        if(friend[v] == v) {
            return v;
        }

        friend[v] = findFriend(friend[v]);
        return friend[v];
    }

    static void union(int a, int b) {
        int fa = findFriend(a);
        int fb = findFriend(b);

        if(fa!=fb) {
            friend[fb] = fa;
        }
    }

    static Map<Integer, List<Integer>> enemies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        friend = new int[n + 1];
        enemies = new HashMap<>();
        for(int i = 1;i<=n;i++) {
            friend[i] = i;
            enemies.put(i, new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for(int i= 0;i<m;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);

            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            if(c == 'F') {
                union(p, q);
            }
            else {
                for (Integer enemy : enemies.get(p)) {
                    union(q, enemy);
                }
                for (Integer enemy : enemies.get(q)) {
                    union(p, enemy);
                }
                enemies.get(p).add(q);
                enemies.get(q).add(p);
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 1;i<=n;i++) {
            set.add(findFriend(friend[i]));
        }
//        System.out.println(set);
        System.out.println(set.size());

    }
}
