
import java.io.*;
import java.util.*;

public class Main {

    static int[] valueMap;
    static boolean[] visited;

    static long[] sum;

    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static HashSet<Integer> startPoint = new HashSet<>();
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
        int N = Integer.parseInt(br.readLine());
        valueMap = new int[N + 1];
        sum = new long[N+1];
        visited = new boolean[N + 1];
        for(int i= 1;i<=N;i++) {
            map.put(i, new ArrayList<>());
            startPoint.add(i);
        }

        
        for(int i= 1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(st.nextToken());
            valueMap[i] = value;
            
            int input;

            while(st.hasMoreTokens()&&(input = Integer.parseInt(st.nextToken()))!=-1) {
                map.get(i).add(input);
                startPoint.remove(input);

            }
            
        }
        for (Integer start : startPoint) {
            dfs(start);
        }
        for(int i= 1;i<=N;i++) {
            bw.write(sum[i] + "\n");
        }

        bw.flush();
        bw.close();

        

    }
}

