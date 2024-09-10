
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int y,x;
        int value;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }

        public Point(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }
    static class Edge {
        // dest
        int y, x;
        int hole;

        public Edge(int y, int x, int hole) {
            this.y = y;
            this.x = x;
            this.hole = hole;
        }
    }

    static class Node {
        List<Edge> edges;
        public Node(
            List<Edge> edges
        ) {
            this.edges = edges;
        }
    }
    static Node[][] tanks;
    static int N,M, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tanks = new Node[N][M];

        int[][] capacity = new int[N][M];

        for(int i= 0;i<N;i++) {
            Arrays.fill(capacity[i], H);
        }

        for(int i = 0;i<N;i++) {
            for (int j = 0; j < M; j++) {
                tanks[i][j] = new Node(new ArrayList<>());
            }
        }

        // N+1, M, 가로 벽

        PriorityQueue<Point> q = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1.value, o2.value));
        // 첫 번째는 외부랑 통함
        int i = 0;
        st = new StringTokenizer(br.readLine());
        for(int j= 0;j<M;j++) {
            int hole = Integer.parseInt(st.nextToken());
            if (hole == -1) continue;
            q.add(new Point(i, j, hole));
            capacity[i][j] = Math.min(capacity[i][j], hole);
        }
        for (i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int hole = Integer.parseInt(st.nextToken());
                if(hole == -1) continue;
                // y-1이랑 y간에 이어주는 역할
                Node upper = tanks[i - 1][j];
                Node lower = tanks[i][j];
                upper.edges.add(new Edge(i, j, hole));
                lower.edges.add(new Edge(i - 1, j, hole));

            }
        }
        // 마지막도 외부랑 통함
        st = new StringTokenizer(br.readLine());
        for(int j= 0;j<M;j++) {
            int hole = Integer.parseInt(st.nextToken());
            if (hole == -1) continue;
            q.add(new Point(N - 1, j, hole));
            capacity[N - 1][j] = Math.min(capacity[N - 1][j], hole);

        }
////////////////////////////////////////
        // N, M+1, 세로 벽

        for (i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 첫 번째는 외부랑 통함
            int j = 0;
            int hole = Integer.parseInt(st.nextToken());
            if (hole != -1) {
                q.add(new Point(i, j, hole));
                capacity[i][j] = Math.min(capacity[i][j], hole);

            }

            for (j = 1; j < M; j++) {
                 hole = Integer.parseInt(st.nextToken());

                if (hole == -1) continue;

                Node left = tanks[i][j-1];
                Node right = tanks[i][j];
                left.edges.add(new Edge(i, j, hole));
                right.edges.add(new Edge(i, j-1, hole));

            }

            // 마지막도 외부랑 통함
            hole = Integer.parseInt(st.nextToken());
            if (hole != -1) {
                q.add(new Point(i, M - 1, hole));
                capacity[i][M - 1] = Math.min(capacity[i][M - 1], hole);
            }
        }

//        for (int[] ints : capacity) {
//            System.out.println(Arrays.toString(ints));
//
//        }

        while(!q.isEmpty()) {
            Point now = q.poll();
            if(now.value > capacity[now.y][now.x]) continue;
            Node tank = tanks[now.y][now.x];
            for (Edge edge : tank.edges) {
                int min = -1;

                // 현재 < 다음
                if (now.value < capacity[edge.y][edge.x]){
                    // 현재보다 구멍이 위에 있으면
                    min = Math.max(now.value, edge.hole);
                } else {
                    continue;
                }
                if (capacity[edge.y][edge.x] > min) {
                    capacity[edge.y][edge.x] = min;
                    q.add(new Point(edge.y, edge.x, min));
                }
            }
        }

        int sum = 0;
        for (int[] ints : capacity) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
//        for (int[] ints : capacity) {
//            System.out.println(Arrays.toString(ints));
//
//        }
//
        System.out.println(sum);

//        System.out.println(q);



    }

}
