import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static int[] ary;
    static int k,N;

    static boolean dfs(int nowIndex, int num, int base) {

//        System.out.println("nowIndex = " + nowIndex);
//        System.out.println("base+\" \"+num = " + base + " " + num);
        if(nowIndex > N) {
            return true;
        }
        if(base+num > N) {
            return false;
        }
        ary[nowIndex] = base + num;
        set.remove(base + num);

        if (!dfs(nowIndex * 2 , 2 * num + 1, base)) {
            return false;
        }
        if (!dfs(nowIndex * 2 + 1, 2 * num + 2, base)) {
            return false;
        }
        return true;
    }

    static TreeSet<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        ary = new int[N + 1];
        set = IntStream.range(1, N + 1)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));


        k = K-1;
        int id = p/2;
        while(id>=1) {
            if(k<=0) {
                System.out.println(-1);
                return;
            }
            set.remove(k);
            ary[id] = k--;
            id/=2;
        }
        id = p;
        k = K;
        if(!dfs(id, 0, K)){
            System.out.println(-1);
            return;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 1;i<=N;i++) {
            if(ary[i]==0) {
                ary[i] = set.first();
                set.remove(ary[i]);
            }
            bw.write(ary[i] + "\n");
        }

        bw.flush();bw.close();



    }
}
