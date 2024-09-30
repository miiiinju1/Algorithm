
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[][] adj;
    static class Point {

        int y, x;
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

    static void markWall(int[][] visited, int[][] map) {
        for(int i = 0;i<map.length;++i) {
            for(int j= 0;j<map[0].length;++j) {
                if (map[i][j] == -1) {
                    visited[i][j] = -1;

                }
            }
        }
    }

    static int w,h;
    static void dijkstra(int startY, int startX, int[][] map) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(startY, startX, 0));

        int[][] distance = new int[h][w];
        for(int i = 0;i<h;++i) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        distance[startY][startX] = 0;
        markWall(distance, map);

        int start = map[startY][startX];

        while(!q.isEmpty()) {
            Point now = q.poll();

            if (map[now.y][now.x] >= 0 && map[now.y][now.x] != Integer.MAX_VALUE) {
                int end = map[now.y][now.x];
                adj[start][end] = now.value;
                adj[end][start] = now.value;
            }

            for (int d = 0; d < 4; ++d) {
                int y = now.y + dy[d];
                int x = now.x + dx[d];
                if (isValid(y, x) && map[y][x] >= 0
                    && distance[y][x] > distance[now.y][now.x] + 1) {
                    distance[y][x] = distance[now.y][now.x] + 1;
                    q.add(new Point(y, x, now.value + 1));
                }
            }

        }
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < h && x < w;
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};


    static void check(int[] ary) {

//        System.out.println("Arrays.toString(ary) = " + Arrays.toString(ary));
        int now = 0;

        long temp = 0;
        for (int i = 0; i < ary.length; ++i) {
            temp += adj[now][ary[i]];
            now = ary[i];
        }

        min = Math.min(temp, min);
    }

    static long min = 0;
    static void permutation(int nowIndex, int end, int count, int[] ary, boolean[] visited) {

        if (nowIndex == count) {
            // ary
            check(ary);
            return;
        }

        for (int i = 1; i <= end; ++i) {
            if(!visited[i]) {
                ary[nowIndex] = i;
                visited[i] = true;
                permutation(nowIndex + 1, end, count, ary, visited);
                visited[i] = false;
            }
        }

    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(;;) {
            min = Integer.MAX_VALUE;
            var st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }
            int[][] map = new int[h][w];
            for (int i = 0; i < h; ++i) {
                Arrays.fill(map[i], Integer.MAX_VALUE);
            }

            int dirtyCount = 0;
            for (int i = 0; i < h; ++i) {
                String line = br.readLine();
                for (int j = 0; j < line.length(); ++j) {
                    char c = line.charAt(j);
                    switch (c) {
                        case '*':
                            map[i][j] = ++dirtyCount;
                            break;
                        case 'o':
                            map[i][j] = 0;
                            break;
                        case 'x':
                            map[i][j] = -1;
                            break;
                    }

                }
            }

            adj = new int[dirtyCount + 1][dirtyCount + 1];

            for (int i = 0; i <= dirtyCount; ++i) {
                Arrays.fill(adj[i], Integer.MAX_VALUE);
            }
            // 0은 시작 지점


            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < w; ++j) {
                    if (map[i][j] >= 0 && map[i][j] != Integer.MAX_VALUE) {
                        // search
                        dijkstra(i, j, map);
                    }
                }
            }
//            Arrays.stream(adj).forEach(a -> System.out.println(Arrays.toString(a)));

            // 순열
            // 4 8

            // 8 + 32 + 8 8

            // 50

            permutation(0, dirtyCount, dirtyCount, new int[dirtyCount],
                new boolean[dirtyCount + 1]);
            bw.write((min != Integer.MAX_VALUE ? min : -1) + "\n");
        }
        bw.flush();bw.close();
        // 직사각형 모양

        /*


방은 정사각형 칸으로 나누어짐
         로봇 청소기의 크기도 1×1



.......
.o...*.
.......
.*...*.
.......

 더러운 칸의 개수는 10개임

 따라서 모든 간선을 가지고 있다고 간주

 // 10개니깐, 순서를 정하자


 // 10!는 5050뿐임

 따라서, 10!가지의 순서대로 방문하는 경우를 ex, 13245, 14235의 길이를 재자

 // 근데 , -> 순서대로 갈 때, 아 최단 경로를 미리 지정해두기?

 // 1. 각 노드 간에 최단 경로로 인접 리스트 만들어두기

 // 2. 10! 모든 경우에 대해 계산하기




// ex

0 - 1
 \  /
   2



0 - 1
|
2

        * */

    }

}
//5 5
//*x*x*
//.....
//*xox*
//.....
//*x*x*

//