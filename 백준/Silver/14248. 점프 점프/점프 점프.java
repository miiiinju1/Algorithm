import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] ary = new int[n+1];
        for(int i=1;i<=n;i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        int start = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[n+1];
        visited[0] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int count = 0;
        while(!q.isEmpty()) {
            int now = q.poll();
            if (visited[now]) {
                continue;
            }
            visited[now] = true;
            count++;
            int next = ary[now];
            for(int i= 0;i<2;i++) {
                next*=-1;
                if(now+next>0&&now+next<=n)
                     q.add(now+next);
            }

        }
        System.out.println(count);


    }
}
