
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int startY = Integer.parseInt(st.nextToken())-1;
        int startX = Integer.parseInt(st.nextToken())-1;
        int endY = Integer.parseInt(st.nextToken())-1;
        int endX = Integer.parseInt(st.nextToken())-1;

        int[][] map = new int[N][M];
        int[][][] distance = new int[N][M][3];
        for(int i =0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<M;j++)
            Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            for(int j= 0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        distance[startY][startX][1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(startY, startX, 0, 1));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

//            System.out.println("now.y+\" \"+now.x = " + now.y + " " + now.x);

            int nextTime = (now.time + 1)%3;
            if (now.time % 3==0) {
                for (int d = 0; d < 4; d++) {
                    int Y = dy[d] + now.y;
                    int X = dx[d] + now.x;

                    if (isValid(Y, X) &&  map[Y][X]!=-1 &&distance[Y][X][nextTime] > now.cost + map[Y][X]) {
                        distance[Y][X][nextTime] = now.cost + map[Y][X];
                        pq.add(new Node(Y, X, now.cost + map[Y][X], now.time + 1));
                    }
                }
            } else if(now.time %3==1) {
                for (int d = 0; d < 2; d++) {
                    int Y = dy[d] + now.y;
                    int X = dx[d] + now.x;

                    if (isValid(Y, X) && map[Y][X]!=-1 &&distance[Y][X][nextTime] > now.cost + map[Y][X]) {
                        distance[Y][X][nextTime] = now.cost + map[Y][X];
                        pq.add(new Node(Y, X, now.cost + map[Y][X], now.time + 1));
                    }
                }

            }
            else {

                for (int d = 2; d < 4; d++) {
                    int Y = dy[d] + now.y;
                    int X = dx[d] + now.x;
                    if (isValid(Y, X) &&  map[Y][X]!=-1 &&distance[Y][X][nextTime] > now.cost + map[Y][X]) {
                        distance[Y][X][nextTime] = now.cost + map[Y][X];
                        pq.add(new Node(Y, X, now.cost + map[Y][X], now.time + 1));
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i= 0;i<3;i++) {
            min = Math.min(min, distance[endY][endX][i]);
        }

        if(min==Integer.MAX_VALUE) {
            System.out.println(-1);

            return;
        }

        System.out.println(min);



    }

    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }
    static class Node implements Comparable<Node>{
        int y,x,cost, time;

        public Node(int y, int x, int cost, int time) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

}
