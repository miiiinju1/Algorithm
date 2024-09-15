
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }

        int p = parent[v];
        int fP = find(p);
//        System.out.println("dV = " + distance[v]);
//        System.out.println("dP = " + distance[p]);
        distance[v] += distance[p];

        parent[v] = fP;

        return parent[v];
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        int distB = distance[b];
        int dist = Math.abs(a-b)%1000;
        if(fa!=fb) {
            parent[fa] = fb;
            distance[fa] = (distB + dist);
        }

    }
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; ++tc) {

            int N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];
            distance = new int[N + 1];
            for(int i = 1;i<=N;i++) {
                parent[i] = i;
            }
            Arrays.fill(distance, 0);

            String input;
            loop: while ((input = br.readLine()) != null) {
                var st = new StringTokenizer(input);
                switch (st.nextToken()) {
                    case "E":
                        int centerI = Integer.parseInt(st.nextToken());
                        // 출력
                        find(centerI);
                        bw.write(distance[centerI] + "\n");
//                        System.out.println("distance = " + distance[centerI]);
//
//                        System.out.println(Arrays.toString(distance));
//                        System.out.println(Arrays.toString(parent));
//
//                        System.out.println();
                        break;
                    case "I":
                        // 통합
                        int I = Integer.parseInt(st.nextToken());
                        int J = Integer.parseInt(st.nextToken());
                        // I의 부모를 J로

                        union(I, J);

                        // I쪽에 해당하는 애들은 싹 다 증가시키기

                        // I와 J의 차이만큼의 길 만들기


                        break;
                    default:
                        break loop;
                }




            }






        }
                bw.flush();bw.close();

    }

}


/*
*
*
*   1 - 2 - 3()            4 - 5 - 6
*
*
*
*  3 - 1 - 2()
*
*
*
*
*
*
*
* */