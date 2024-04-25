import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static List<Node>[][] map;
    static int[][] value;

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static class Node {
        int y, x, cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1][M + 1];
        value = new int[N + 1][M + 1];
        for(int i= 0;i<=N;i++) {
            for(int j= 0;j<=M;j++) {
                map[i][j] = new ArrayList<>();
                Arrays.fill(value[i], Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < N; i++) {

            final String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if(s.charAt(j)=='/') {
                    map[i + 1][j].add(new Node(i, j + 1, 0));
                    map[i][j + 1].add(new Node(i + 1, j, 0));
                    
                    map[i][j].add(new Node(i + 1, j + 1, 1));
                    map[i + 1][j + 1].add(new Node(i, j, 1));
                }
                else {
                    map[i][j].add(new Node(i + 1, j + 1, 0));
                    map[i + 1][j + 1].add(new Node(i, j, 0));

                    map[i + 1][j].add(new Node(i, j + 1, 1));
                    map[i][j + 1].add(new Node(i + 1, j, 1));
                }
            }
        }


        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0));
        value[0][0] = 0;

        while (!q.isEmpty()) {
            final Point now = q.removeFirst();

            final List<Node> nodes = map[now.y][now.x];

            for (Node node : nodes) {
                if(value[node.y][node.x] > value[now.y][now.x] + node.cost) {
                    value[node.y][node.x] = value[now.y][now.x] + node.cost;

                    if(node.cost==0) {
                        q.addFirst(new Point(node.y, node.x));
                    }
                    else {
                        q.addLast(new Point(node.y, node.x));
                    }
                }

            }
        }
        if (value[N][M] == Integer.MAX_VALUE) {
            System.out.println("NO SOLUTION");
        } else {
            System.out.println(value[N][M]);
        }

    }
}
