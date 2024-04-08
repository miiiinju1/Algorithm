import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Dest {
        int y,x, cost;

        public Dest(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }

    static int[][] map;
    static class Point {
        int y,x,depth;

        public Point(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }

    }

    static int[] dy = {-1,0,0,1};
    static int[] dx = {0,-1,1,0};
    static int shortest(int startY, int startX, int destY, int destX) {
        boolean[][] visited = new boolean[map.length][map.length];

        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(startY, startX, 0));
        visited[startY][startX] = true;

        while(!q.isEmpty()) {
            final Point now = q.poll();

            if(now.y == destY && now.x == destX) {
                return now.depth;
            }

            for(int i =0;i<4;i++) {
                int y = dy[i]+now.y;
                int x = dx[i]+now.x;

                if(y>=0&&x>=0&&y<map.length &&x<map.length&&!visited[y][x] && map[y][x]!=-1) {
                    visited[y][x] = true;
                    q.add(new Point(y, x, now.depth + 1));
                }
            }
        }
        return Integer.MAX_VALUE;

    }

    static class Result {
        int start, value;

        public Result(int start, int value) {
            this.start = start;
            this.value = value;
        }
    }
    static Result find(int startY, int startX) {
        boolean[][] visited = new boolean[map.length][map.length];

        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(startY, startX, 0));
        visited[startY][startX] = true;

        int minCost = Integer.MAX_VALUE;

        PriorityQueue<Point> pq = new PriorityQueue<>((o1,o2) -> {
            if(o1.y==o2.y) {
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o1.y, o2.y);
        });
        while(!q.isEmpty()) {
            final Point now = q.poll();

            if(map[now.y][now.x] > 0 && now.depth <= minCost) { // 조건 다시 봐야할듯 여기 왜냐면 거리 같은 경우 우선순위가 있음

                minCost = Math.min(minCost, now.depth);
                pq.add(now);
                continue;
            }

            else if(now.depth>minCost) {
                break;
            }
            for(int i =0;i<4;i++) {
                int y = dy[i]+now.y;
                int x = dx[i]+now.x;

                if(y>=0&&x>=0&&y<map.length &&x<map.length&&!visited[y][x] && map[y][x]!=-1) {
                    visited[y][x] = true;
                    q.add(new Point(y, x, now.depth + 1));
                }
            }
        }

        if(pq.isEmpty()) {
            return new Result(-1, Integer.MAX_VALUE);

        }
        final Point now = pq.poll();
        int temp = map[now.y][now.x];
        map[now.y][now.x]=0;//중요
        return new Result(temp, now.depth);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                int temp = Integer.parseInt(st.nextToken());
                if(temp==1) {
                    temp = -1;
                }
                map[i][j] = temp;
            }
        }

        st = new StringTokenizer(br.readLine());

        HashMap<Integer, Dest> destination = new HashMap<>();

        int nowY = Integer.parseInt(st.nextToken())-1;
        int nowX = Integer.parseInt(st.nextToken())-1;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int destY = Integer.parseInt(st.nextToken())-1;
            int destX = Integer.parseInt(st.nextToken())-1;


            map[y][x] = i;
            int distance = shortest(y,x,destY,destX);

            destination.put(i, new Dest(destY, destX, distance));

        }


        while(fuel>0 && destination.size()>0) {

            final Result result = find(nowY, nowX);

            fuel -= result.value;

            if(fuel<0) {
                break;
            }
//            System.out.println("fuel = " + fuel);

//            System.out.println(result.start+" "+result.value);

            if(result.start==-1) {
                System.out.println(-1);
                return ;
            }
            final Dest dest = destination.get(result.start);
//            System.out.println("dest.cost = " + dest.cost);

            nowY = dest.y;
            nowX = dest.x;
            fuel -= dest.cost;
            destination.remove(result.start);
            if(fuel>=0) {
                fuel+=dest.cost*2;
            }
            else {
                break;
            }
        }


        if(fuel<0) {
            System.out.println(-1);
            return;
        }

        System.out.println(fuel);

    }
}
