import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] map;
    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static boolean isValid(int y, int x) {
        return y>=0&&x>=0&&y<N&&x<M;
    }
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,-1,0,1};

    static HashMap<Integer, List<Point>> islands = new HashMap<>();
    static void search() {
        boolean[][] visited = new boolean[N][M];
        Deque<Point> q = new ArrayDeque<>();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    count+=1;
                    islands.put(count, new ArrayList<>());
                    q.add(new Point(i, j));
                    while(!q.isEmpty()) {
                        final Point now = q.poll();

                        map[now.y][now.x] = count;
                        islands.get(count).add(now);
                        for(int d = 0;d<4;d++) {
                            int Y = dy[d]+now.y;
                            int X = dx[d]+now.x;
                            if(isValid(Y,X) && map[Y][X] == 1 && !visited[Y][X]) {
                                visited[Y][X] = true;
                                q.add(new Point(Y, X));
                            }
                        }
                    }
                }
            }
        }

    }
    static class Bridge {
        int from, to, length;

        public Bridge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }
    }

    static Bridge buildBridge(int y, int x, int d, int from, int length) {

        if(map[y][x] != from && map[y][x] != 0) {
            if(length>=2)
                return new Bridge(from, map[y][x], length);
            return null;
        }

        int Y = dy[d]+y;
        int X = dx[d]+x;

        if(isValid(Y,X)) {
            if (map[y][x] == 0) {
                return buildBridge(Y, X, d, from, length + 1);
            }
            else if(map[y][x] == from) {
                return buildBridge(Y, X, d, from, 0);
            }
            return buildBridge(Y, X, d, from, length);
        }
        return null;
    }
    static PriorityQueue<Bridge> candidate() {

        PriorityQueue<Bridge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.length));

        int islandsCount = islands.size();
        int[][] connected = new int[islandsCount+1][islandsCount+1];

        for(int i = 0;i<=islandsCount;i++) {
            Arrays.fill(connected[i], Integer.MAX_VALUE);
        }

        for (Map.Entry<Integer, List<Point>> entry : islands.entrySet()) {

            int from = entry.getKey();
            
            final List<Point> ground = entry.getValue();
            for (Point point : ground) {
                for(int d = 0;d<4;d++) {
                    Bridge result = buildBridge(point.y, point.x, d, from, 0);
                    if(result!=null && connected[result.from][result.to] > result.length &&  result.length>=2) {
                        connected[result.from][result.to] = result.length;
                        connected[result.to][result.from] = result.length;
                        pq.add(result);
                    }
                }
            }
        }


        return pq;
    }

    static int[] parent;

    static int find(int v) {
        if(parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            parent[fa] = fb;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         map = new int[N][M];
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        search();

        parent = new int[islands.size()+1];
        for(int i= 0;i<= islands.size();i++) {
            parent[i] = i;
        }
        final PriorityQueue<Bridge> bridges = candidate();

        int sum = 0;
        int count = 0;
        while(!bridges.isEmpty()) {
            final Bridge poll = bridges.poll();
            if (union(poll.from, poll.to)) {
                sum+=poll.length;
                count+=1;
            }
        }

        if(count+1==islands.size()) {
            System.out.println(sum);
            return;
        }
        System.out.println(-1);
        

    }
}
