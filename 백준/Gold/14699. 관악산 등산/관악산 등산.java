
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

  static Map<Integer, List<Integer>> map = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    var st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] height = new int[N + 1];
    st = new StringTokenizer(br.readLine());
    for(int i= 1;i<=N;i++) {
      map.put(i, new ArrayList<>());
      height[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if (height[a] < height[b]) {
        map.get(a).add(b);
      } else {
        map.get(b).add(a);
      }
    }

    visited = new int[N + 1];
    Arrays.fill(visited, Integer.MAX_VALUE);

    for (int i = 1; i <= N; i++) {
      if(visited[i] == Integer.MAX_VALUE) {
        dfs(i);
      }

    }

    for(int i = 1;i<=N;i++) {
      bw.write(visited[i] + "\n");
    }bw.flush();bw.close();

  }
  static int[] visited;

  static int dfs(int now) {

    int temp = 0;
    for (Integer next : map.get(now)) {

      if (visited[next] == Integer.MAX_VALUE) {
        temp = Math.max(temp, dfs(next));

      } else {
        temp = Math.max(temp, visited[next]);
      }

    }
    visited[now] = temp + 1;
    return visited[now];
  }

}
