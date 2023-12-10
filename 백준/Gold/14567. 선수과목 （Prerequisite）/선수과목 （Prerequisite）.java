import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static int dfs(int now) {

        if(visitTime[now]!=-1) {
            return visitTime[now];
        }
        int time = 1;
        for (Integer integer : map.get(now)) {
            time = Math.max(dfs(integer)+1, time);
        }
        visitTime[now] = time;
        return time;
    }

    static int[] visitTime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        visitTime = new int[N + 1];

        Arrays.fill(visitTime, -1);

        HashSet<Integer> start = new HashSet<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            start.remove(A);
            start.add(B);
            map.get(B).add(A);
        }

        for (Integer integer : start) {
            dfs(integer);
        }

        for(int i= 1;i<=N;i++) {
            if(visitTime[i]==-1)
                visitTime[i] = 1;
            bw.write(visitTime[i]+" ");
        }
        bw.flush();bw.close();


    }
}
