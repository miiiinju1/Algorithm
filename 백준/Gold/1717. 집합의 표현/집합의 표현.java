
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] map;
    static int[] sizeMap;
    static int find(int v) {
        if(map[v] == v) {
            return v;
        }
        map[v] = find(map[v]);
        return map[v];

    }
    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            if(sizeMap[fa]>sizeMap[fb]) {
                int temp = fa;
                fa = fb;
                fb = temp;
            }
            map[fa] = fb;
            sizeMap[fa] = sizeMap[fa]+sizeMap[fb];
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        map = new int[n+1];
        sizeMap = new int[n+1];
        for(int i = 0;i<=n;i++) {
            map[i] = i;
        }

        int M = Integer.parseInt(st.nextToken());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(type==0) {
                union(a,b);
            }
            else {
                int fa = find(a);
                int fb = find(b);

                if(fa==fb) {
                    bw.write("YES\n");
                }
                else {
                    bw.write("NO\n");

                }


            }
        }
        bw.flush();bw.close();
    }
}
