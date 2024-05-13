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
    static boolean[] cut;

    static class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
//    static Map<Integer, Pair> infinityMap = new HashMap<>();
    static Set<Integer> infinityMap = new HashSet<>();
    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            //B가 나가는 방향이니깐
            parent[fa] = fb;
        }
        else {//if(fb != b) {
            // INFINITY임
            infinityMap.add(fb);//(fa, new Pair(a, b));
        }
    }



    static int N;

    static Pair[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        cut = new boolean[N + 1];

        for(int i= 0;i<=N;i++) {
            parent[i] = i;
        }
        list = new Pair[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i= 1;i<=N;i++) {
            int b = Integer.parseInt(st.nextToken());
            list[i] = new Pair(i, b);
        }

        int Q = Integer.parseInt(br.readLine());

        List<Pair> queries = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            queries.add(new Pair(a, b));

            if(a==2) {
                cut[b] = true;
            }
        }
        for(int i = 1;i<=N;i++) {
            Pair pair = list[i];
            if (!cut[pair.a]) {
                if(pair.b!=0)
                    union(pair.a, pair.b);
            }
        }

        Collections.reverse(queries);
        List<String> res = new ArrayList<>();
        for(int i = 0;i<Q;i++) {
            Pair query = queries.get(i);

            if (query.a == 1) {
                int result = find(query.b);

                if (infinityMap.contains(result)) {
//                    bw.write("CIKLUS\n");
                    res.add("CIKLUS\n");
                } else {
//                    bw.write(result + "\n");
                    res.add(result + "\n");
                }
            }
            else {
                final Pair pair = list[query.b];
                union(pair.a, pair.b);
            }
//            System.out.println("command "+query.a + " " + query.b);
//            for(int j= 1;j<=N;j++) {
//                System.out.print(parent[j] + " ");
//            }
//            System.out.println();
        }
        Collections.reverse(res);

        for (String str : res) {
            bw.write(str);
        }


        bw.flush();bw.close();
    }
}
