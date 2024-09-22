
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n,k;
    static boolean isValid(int y, int x) {
        return y > 0 && x > 0 && y <= n && x <= n;
    }
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];
        int[][] distance = new int[n + 1][n + 1];
        int[][] prev = new int[n + 1][n + 1];

        for(int i = 0;i<=n;++i) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            Arrays.fill(map[i], 1);
        }

        var st = new StringTokenizer(br.readLine());

        Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

         k = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());

            int C = Integer.parseInt(st.nextToken());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = k;
            for (int c = 1; c < C - 1; ++c) {
                int newY = Integer.parseInt(st.nextToken());
                int newX = Integer.parseInt(st.nextToken());
                // 만약 y가 같으면 x만 new - now 씩 증가
                makring(y, x, newY, newX);
                y = newY;
                x = newX;

            }
            int newY = Integer.parseInt(st.nextToken());
            int newX = Integer.parseInt(st.nextToken());
            makring(y, x, newY, newX);
            map[newY][newX] = k;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start.y, start.x, 1));
        distance[start.y][start.x] = 1;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.y==end.y && now.x==end.x) {
                bw.write(now.value + "\n");

                break;
            }

            for(int d = 0;d<4;++d) {
                int Y = dy[d] + now.y;
                int X = dx[d] + now.x;
                if(isValid(Y,X)) {
                    int nextValue = now.value + map[Y][X];
                    if (distance[Y][X] > nextValue) {
                        distance[Y][X] = nextValue;
                        prev[Y][X] = (d + 2) % 4;
                        pq.add(new Node(Y, X, nextValue));
                    }
                }
            }

        }


        // 방향 만들기

        int nowDir = prev[end.y][end.x];
        int y = end.y;
        int x = end.x;
        List<int[]> res = new ArrayList<>();
        while(true) {

            y = y + dy[nowDir];
            x = x + dx[nowDir];
            if(y==start.y && x == start.x) {
                break;
            }
            int nextDir = prev[y][x];

            if(nowDir != nextDir) {
                res.add(new int[]{y, x});
                nowDir = nextDir;
            }
        }
        bw.write((res.size() + 2) + " ");

        Collections.reverse(res);
        bw.write(start.y + " ");
        bw.write(start.x + " ");
        for (int[] re : res) {
            bw.write(re[0] + " " + re[1] + " ");
        }
        bw.write(end.y + " ");
        bw.write(end.x + "");
        bw.flush();bw.close();



//        for(int i = 0;i<=n;++i) {
//            System.out.println(Arrays.toString(distance[i]));
//
//        } for(int i = 0;i<=n;++i) {
//            System.out.println(Arrays.toString(map[i]));

//        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static class Node implements Comparable<Node> {
        int y,x,value;

        public Node(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }

    static void makring(int y, int x, int newY, int newX) {
        if(newY==y) {
            if(newX>x) {
                for (; x < newX; ++x) {
                    map[y][x] = k;
                }
            } else {
                for (; x > newX; --x) {
                    map[y][x] = k;
                }
            }
        } else {
            if(newY>y) {
                for (; y < newY; ++y) {
                    map[y][x] = k;
                }
            } else {
                for(; y>newY;--y) {
                    map[y][x] = k;
                }
            }
        }
    }
    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
