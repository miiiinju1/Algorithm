import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static class Node {
        int y, x, depth;

        public Node(int y, int x,int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
    }

    static class Point implements Comparable<Point> {
        int y,x;
        int min;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        

        public Point(int y, int x, int min) {
            this.y = y;
            this.x = x;
            
            this.min = min;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(o.min,this.min);
        }
    }

    static void distance (List<Node> trees) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        for (Node tree : trees) {
            distanceMap[tree.y][tree.x] = tree.depth;
            q.add(tree);
        }
        while(!q.isEmpty()) {
            final Node now = q.poll();

            for(int i= 0;i<4;i++) {
                int Y = now.y + dy[i];
                int X = now.x + dx[i];
                if(Y>=0&&X>=0&&Y<N&&X<M&&distanceMap[Y][X]>now.depth+1) {
                    distanceMap[Y][X] = now.depth + 1;
                    q.add(new Node(Y, X, now.depth + 1));
                }
            }
        }
    }


    static int N,M;
    static int[][] distanceMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        ArrayList<Node> trees = new ArrayList<>();
        boolean[][] visited = new boolean[N][M];
        distanceMap = new int[N][M];
        for(int i= 0;i<N;i++) {
            Arrays.fill(distanceMap[i],100000);
        }
        Point start = null;
        Point target = null;
        for(int i= 0;i<N;i++) {
            map[i] = br.readLine().toCharArray();

            for(int j= 0;j<M;j++) {

                if(map[i][j]=='+') {
                    trees.add(new Node(i, j,0));
                } else if (map[i][j] == 'V') {
                    start = new Point(i, j);
                } else if (map[i][j] == 'J') {
                    target = new Point(i, j);
                }
            }

        }

        distance(trees);
        PriorityQueue<Point> pq = new PriorityQueue<>();

        int temp = distanceMap[start.y][start.x];
        pq.add(new Point(start.y, start.x,temp));

        visited[start.y][start.x] = true;

        while (!pq.isEmpty()) {
            final Point now = pq.poll();

            if(now.y==target.y&&now.x==target.x) {
                System.out.println(now.min);
                return ;
            }
            for(int d= 0;d<4;d++) {
                int y = now.y + dy[d];
                int x = now.x + dx[d];

                if(y>=0&&x>=0&&y<N&&x<M&&!visited[y][x]&&map[y][x]!='+') {
                    int distance = distanceMap[y][x];
                    pq.add(new Point(y, x, Math.min(now.min, distance)));
                    visited[y][x] = true;
                }
            }
        }
        System.out.println(0);





    }
}
