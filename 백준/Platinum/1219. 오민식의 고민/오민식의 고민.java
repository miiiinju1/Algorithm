import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static class Edge {

    int from, to, cost;


    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  static List<Edge> edges = new ArrayList<>();


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    var st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    for (int i = 0; i < M; i++) {
      //시작 끝 가격
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      edges.add(new Edge(a, b, cost));
    }

    int[] cost = new int[N];

    st = new StringTokenizer(br.readLine());

    for(int i = 0;i<N;i++) {
      cost[i] = Integer.parseInt(st.nextToken());
    }

    long[] distance = new long[N];

    Arrays.fill(distance, Long.MIN_VALUE);
    distance[start] = cost[start];

    for (int i = 1; i < N; i++) {
      for (Edge edge : edges) {
        long weight = -edge.cost + distance[edge.from] + cost[edge.to];
        if (distance[edge.from] != Long.MIN_VALUE && distance[edge.to] < weight) {
          distance[edge.to] = weight;
        }
      }
    }

    if (distance[end] == Long.MIN_VALUE) {
      System.out.println("gg");
      return;
    }

    for (Edge edge : edges) {
      long weight = -edge.cost + distance[edge.from] + cost[edge.to];
      if (distance[edge.from] != Long.MIN_VALUE && distance[edge.to] < weight) {
        if(isCycle(edge.from, end, N)) {
          System.out.println("Gee");
          return;
        }
      }
    }

    System.out.println(distance[end]);




  }

  static boolean[] visited;
  private static boolean isCycle(int from, int to, int N) {
    visited = new boolean[N];

    visited[from] = true;
    return dfs(from, to);
  }

  static boolean dfs(int now, int target) {
    if(now == target) {
      return true;
    }
    for (Edge edge : edges) {
      if(!visited[edge.to] && edge.from == now) {
        visited[edge.to] = true;
        if(dfs(edge.to, target)) {
          return true;
        }
      }
    }
    return false;
  }

}

//4 0 1 4
//0 1 0
//3 1 10
//2 3 3
//3 2 3
//0 10 10 10

//4 0 3 4
//0 1 0
//0 3 5
//1 2 0
//2 1 0
//0 5 5 10