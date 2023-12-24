import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    static int[] size;
    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            if(size[fa]>size[fb]) {
                int temp = fa;
                fa = fb;
                fb = temp;
            }
            parent[fa] = fb;
            size[fb] += size[fa];
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for(int i =0;i<N;i++) {
            parent[i]=i;
        }
        size = new int[N];
        int M = Integer.parseInt(st.nextToken());

        for(int m = 1;m<=M;m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            if(!union(a,b)) {
                System.out.println(m);
                return ;
            }



        }
        System.out.println(0);



    }
}
