
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static final int UP = 0;
    static final int LEFT = 0;
    static final int DOWN = 0;
    static final int RIGHT = 0;

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

        ArrayDeque<Point> q = new ArrayDeque<>();
        // 첫 번째는 외부랑 통함
        int i = 0;
        st = new StringTokenizer(br.readLine());
        for(int j= 0;j<M;j++) {
            int input = Integer.parseInt(st.nextToken());
            if (input == -1) continue;
            q.add(new Point(i, j, input));
            capacity[i][j] = input;
        }
        for (i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                if(input == -1) continue;
                // y-1이랑 y간에 이어주는 역할
                Node upper = tanks[i - 1][j];
                Node lower = tanks[i][j];
                upper.edges.add(new Edge(i, j, input));
                lower.edges.add(new Edge(i-1, j, input));

            }
        }
        // 마지막도 외부랑 통함
        st = new StringTokenizer(br.readLine());
        for(int j= 0;j<M;j++) {
            int input = Integer.parseInt(st.nextToken());
            if (input == -1) continue;
            q.add(new Point(N - 1, j, input));
            capacity[N - 1][j] = input;

        }
////////////////////////////////////////
        // N, M+1, 세로 벽

        for (i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 첫 번째는 외부랑 통함
            int j = 0;
            int input = Integer.parseInt(st.nextToken());
            if (input != -1) {
                q.add(new Point(i, j, input));
                capacity[i][j] = input;

            }

            for (j = 1; j < M; j++) {
                 input = Integer.parseInt(st.nextToken());

                if (input == -1) continue;

                Node left = tanks[i][j-1];
                Node right = tanks[i][j];
                left.edges.add(new Edge(i, j, input));
                right.edges.add(new Edge(i, j-1, input));

            }

            // 마지막도 외부랑 통함
            input = Integer.parseInt(st.nextToken());
            if (input != -1) {
                q.add(new Point(i, M - 1, input));
                capacity[i][M - 1] = input;
            }
        }

        while(!q.isEmpty()) {
            Point now = q.poll();

            Node tank = tanks[now.y][now.x];

            for (Edge edge : tank.edges) {
                int min = Math.max(edge.hole, now.value);
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
        System.out.println(sum);

//        System.out.println(q);



    }

}
