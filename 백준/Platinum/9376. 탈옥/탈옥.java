import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point implements Comparable<Point> {
        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.count, o.count);
        }

        int y, x, count;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }

        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }


    }
    static int H,W;
    static int[][] search(Point start) {
        int[][] visited = new int[H][W];
        for(int i = 0;i<H;i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(start);
        while(!pq.isEmpty()) {
            Point now = pq.poll();

            if(map[now.y][now.x]=='#') {
                now.count++;
            }
            if(visited[now.y][now.x]<=now.count) {
                continue;
            }
            visited[now.y][now.x]=now.count;


            for(int i= 0;i<4;i++) {
                int y = now.y + dy[i];
                int x = now.x + dx[i];

                if(y>=0&&x>=0&&y<H&&x<W&&map[y][x]!='*') {
                    pq.add(new Point(y, x, now.count));
                }
            }

        }
        return visited;

    }
    static char[][] map;

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0, -1, 0, 1};
    static void print(int[][] a) {
        for(int i= 0;i<H;i++) {
            for(int j= 0;j<W;j++) {
                if(a[i][j]==Integer.MAX_VALUE) {
                    System.out.print("X ");
                }
                else
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0;tc<T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map = new char[h+2][w+2];
            H = h+2;
            W = w+2;
            for(int i = 0;i<h+2;i++) {
                for(int j = 0;j<w+2;j++) {
                    map[i][j] = '.';
                }
            }

            ArrayList<Point> start = new ArrayList<>();
            start.add(new Point(0, 0, 0));
            for(int i = 1;i<=h;i++) {
                String str = br.readLine();
                for(int j = 1;j<=w;j++) {
                    map[i][j] = str.charAt(j-1);
                    if(map[i][j]=='$') {
                        start.add(new Point(i, j, 0));
                    }
                }
            }

            int[][] a = search(start.get(0));
//            print(a);
            int[][] b = search(start.get(1));
//            print(b);
            int[][] c = search(start.get(2));

//            print(c);
            int min = Integer.MAX_VALUE;
            for(int i= 0;i<H;i++) {
                for(int j= 0;j<W;j++) {
                    if(map[i][j]=='#') {
                        min = Math.min(min, a[i][j] + b[i][j] + c[i][j]-2);
                    }
                    else {
                        min = Math.min(min,a[i][j] + b[i][j] + c[i][j]);
                    }
                }
            }
            bw.write(min + "\n");

        }
        bw.flush();bw.close();



    }
}
