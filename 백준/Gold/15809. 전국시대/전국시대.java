import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static int N,M;
    static int[] size;
    static int[] parent;

    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }

        parent[v] = find(parent[v]);
        return parent[v];
    }

    static void execute(int a, int b, int type){
        int fa = find(a);
        int fb = find(b);

        if(type==1) {
            //union
            if(size[fa]>size[fb]){
                int temp = fa;
                fa = fb;
                fb = temp;
            }
            parent[fa] = fb;
            int temp = size[fa]+size[fb];
            size[fa] = temp;
            size[fb] = temp;
        }
        else {
            //fight

            //a가 이김
            if(size[fa]>size[fb]) {
                parent[fb] = fa;
                size[fa] -= size[fb];
                size[fb] = 0;
            }
            //b가 이김
            else if(size[fa]<size[fb]) {
                parent[fa] = fb;
                size[fb] -= size[fa];
                size[fa] = 0;

            }
            else {
                size[fa] = 0;
                size[fb] = 0;
            }


        }


    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        size = new int[N + 1];
        parent = new int[N + 1];
        for(int i= 1;i<=N;i++) {
            parent[i] = i;
            size[i] = Integer.parseInt(br.readLine());
        }
        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            execute(a, b, type);

        }


        StringBuilder sb = new StringBuilder();

        HashSet<Integer> set = new HashSet<>();
        for(int i= 1;i<=N;i++) {
            set.add(find(i));
        }


        ArrayList<Integer> list = set.stream().map(i -> size[i])
                .filter(i -> i != 0)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        bw.write(list.size()+"\n");
        for (Integer i : list) {
            bw.write(i + " ");
        }





        bw.flush();bw.close();
    }
}
