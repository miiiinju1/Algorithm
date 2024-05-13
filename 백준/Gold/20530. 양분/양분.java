
import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;

    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            parent[fa] = fb;
        }
    }

    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;

    static HashSet<Integer> set = new HashSet<>();
    static int dfs(int now, int before) {

        int ret = -1;
//        System.out.println("now = " + now);
        for (Integer next : map.get(now)) {
            if(next==before) continue;
//            if(set.contains(next)) continue;

            if(!visited[next]) {
                visited[next] = true;
                int result = dfs(next, now);
                if(result!=-1 && result!= now) {
//                    System.out.println("next+\" \"+result = " + now+" "+ next+" "+result);

//                    System.out.println("now = " + now);
                    set.add(now);
                    ret = result;
                }
                if(result==now) {
                    set.add(now);
                    return -1;
                }

                if(result!=-1) {
                    return result;

                }
            }
            else {
                set.add(now);
               return next;
            }
        }
        return ret;
    }

    static void tree(int now, int before) {
        for (Integer next : map.get(now)) {
            if(next == before) continue;

            if(!set.contains(next)) {
                union(now, next);
                tree(next, now);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        visited = new boolean[N + 1];

        for(int i= 0;i<=N;i++)
            parent[i] = i;

        for(int i = 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(a, v -> new ArrayList<>());
            map.computeIfAbsent(b, v -> new ArrayList<>());
            map.get(a).add(b);
            map.get(b).add(a);

            //cycle일 때 cycle의 개수 세두기

            //아니면 무조건 1


        }
//        System.out.println("set = " + set);
        dfs(1, -1);

        for (Integer i : set) {
            tree(i, -1);
        }


        int[] find = new int[N + 1];
        for(int i= 1;i<=N;i++) {
            find[i] = find(i);
        }
        for(int i = 0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (find[a]!=find[b]) {
                bw.write("2\n");
            }
            else {
                if(set.contains(a) && set.contains(b)) {
                    bw.write("2\n");
                }
                else {
                    bw.write("1\n");
                }
            }

        }
        bw.flush();
        bw.close();

    }
}
