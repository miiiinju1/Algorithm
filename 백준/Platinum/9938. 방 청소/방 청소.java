import java.io.*;
import java.util.StringTokenizer;

public class Main {
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

        parent[fa] = fb;
        if(fb!=fa) {
            count[fb] += count[fa];
            count[fa] = 0;
        }
        if(count[fb]>=1) {
            count[fb]-=1;
            return true;
        }
        return false;
    }


    static int[] parent;
    static int[] count;
//    static Alchol[] alchols;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        parent = new int[L + 1];
        count = new int[L + 1];

        for(int i= 1;i<=L;i++) {
            parent[i] = i;
            count[i] = 1;
        }

//        alchols = new Alchol[L + 1];
        for(int i = 1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

//            Alchol alchol = new Alchol(a, b, 0);

            if (union(a,b)) {
                bw.write("LADICA\n");
            } else {
                bw.write("SMECE\n");
            }


//            System.out.println(i);
//            for(int j= 1;j<=L;j++) {
//                System.out.print(count[j]+" ");
//            }
//            System.out.println();


        }

        bw.flush();bw.close();
    }

}
