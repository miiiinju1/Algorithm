
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Edge {
        int cost;
        Point point;

        public Edge(int cost, Point point) {
            this.cost = cost;
            this.point = point;
        }
    }
    static int N,M;
    static List<Edge>[][] graph;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1][M + 1];
        dist = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                graph[i][j] = new ArrayList<>();
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '/') {
                    graph[i][j + 1].add(new Edge(0, new Point(i + 1, j)));
                    graph[i + 1][j].add(new Edge(0, new Point(i, j + 1)));
                    
                    // 돌리면 cost 1추가 후 
                    graph[i][j].add(new Edge(1, new Point(i + 1, j + 1)));
                    graph[i + 1][j + 1].add(new Edge(1, new Point(i, j)));
                } else {
                    graph[i][j].add(new Edge(0, new Point(i + 1, j + 1)));
                    graph[i + 1][j + 1].add(new Edge(0, new Point(i, j)));
                    
                    graph[i][j + 1].add(new Edge(1, new Point(i + 1, j)));
                    graph[i + 1][j].add(new Edge(1, new Point(i, j + 1)));
                }
            }
        }
        
        dijkstra();

        if (dist[N][M] == Integer.MAX_VALUE) {
            System.out.println("NO SOLUTION");
        } else {
            System.out.println(dist[N][M]);
        }

    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        dist[0][0] = 0;
        pq.add(new Edge(0, new Point(0, 0)));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int y = now.point.y;
            int x = now.point.x;

            if (dist[y][x] < now.cost) continue;

            for (Edge edge : graph[y][x]) {
                int newCost = now.cost + edge.cost;
                int Y = edge.point.y;
                int X = edge.point.x;

                if (dist[Y][X] > newCost) {
                    dist[Y][X] = newCost;
                    pq.add(new Edge(newCost, new Point(Y, X)));
                }
            }
        }
    }

}
