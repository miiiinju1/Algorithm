
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] valueMap;
    static boolean[] visited;

    static long[] sum;

    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static long dfs(int start) {
        if(visited[start])
            return sum[start];

        long temp = 0;

        visited[start] = true;
        ArrayList<Integer> list = map.get(start);
        for (Integer next : list) {
            if (list.size() == 1) {
                temp += dfs(next);
            } else {
                temp = Math.max(temp ,dfs(next));
            }
        }
        temp+= valueMap[start];
        sum[start] = temp;
        return temp;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0;tc<T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            valueMap = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                int value = Integer.parseInt(st.nextToken());
                valueMap[i] = value;
                map.put(i, new ArrayList<>());
            }
            sum = new long[N + 1];
            visited = new boolean[N + 1];

            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                map.get(Y).add(X);
            }
            int W = Integer.parseInt(br.readLine());
            dfs(W);
            bw.write(sum[W] + "\n");
        }
        bw.flush();
        bw.close();



    }
}
