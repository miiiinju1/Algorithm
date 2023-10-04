import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Integer, ArrayList<Integer>> map;
    static int count =0;

    static boolean[] visited;
    static void dfs(int x)
    {
        count++;
        visited[x]=true;
        while(!map.get(x).isEmpty()){
            int now = map.get(x).remove(0);
            if(!visited[now])
                dfs(now);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        map = new HashMap<>();
        for(int i=1;i<=N;i++)
        {
            map.put(i,new ArrayList<>());
        }
        for(int i=0;i<M;i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.get(b).add(a);
        }
        int X = Integer.parseInt(br.readLine());
        while(!map.get(X).isEmpty())
        {
            int now = map.get(X).remove(0);
            if(!visited[now])
                dfs(now);
        }
        System.out.println(count);
    }
}
