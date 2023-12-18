import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

    static int[] ary;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ary = new int[N+1];

        for(int i = 1;i<=N;i++) {
            map.put(i, new HashSet<>());
        }

        for(int i= 1;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            map.get(b).add(a);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
//        Arrays.fill(visited, -1);
//        q.add(new Phase(ary[1],1));
        q.add(ary[1]);
        visited[ary[1]] = true;
        int count = 1;
        while(!q.isEmpty()) {
           int  now = q.poll();
            while(true) {
                if (count+1<=N&&map.get(now).contains(ary[count + 1])) {
                    count++;
                    visited[count]=true;
                    q.add(ary[count]);
                }
                else {
                    break;
                }
            }
        }
        for(int i =1 ;i<=N;i++) {
            if(!visited[i]) {
                System.out.println(0);
                return ;
            }
        }
        System.out.println(1);

    }
}
