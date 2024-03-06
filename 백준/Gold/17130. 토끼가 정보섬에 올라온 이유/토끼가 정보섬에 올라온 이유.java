import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {-1,0,1};
    static int[] dx = {1,1,1};
    static class Point {
        int y, x, count;

        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] visited = new int[N][M];
        char[][] map = new char[N][M];
        for(int i= 0;i<N;i++) {
            Arrays.fill(visited[i], -1);
        }
         int startY = -1,startX = -1;
        for(int i= 0;i<N;i++) {
            char[] input = br.readLine().toCharArray();
            for(int j= 0;j<M;j++) {
                map[i][j] = input[j];
                if(input[j]=='R') {
                    visited[i][j]=0;
                    startY = i;
                    startX = j;
                }
            }
        }

        int max = 0;
        boolean success = false;


        for(int j = 0;j<M;j++) {
            for(int i = 0;i<N;i++) {
                if(visited[i][j]!=-1) {

                    if(map[i][j]=='O') {
                        max = Math.max(max, visited[i][j]);
                        success= true;
                    }

                    for(int d= 0;d<3;d++) {
                        int y = dy[d] + i;
                        int x = dx[d] + j;

                        if(y>=0&&x>=0&&y<N&&x<M&&map[y][x]!='#'&&visited[y][x]<visited[i][j]+1) {
                            int count = visited[i][j];
                            if(map[y][x]=='C') {
                                count++;
                            }
                            visited[y][x] = count;
//                            q.add(new Point(y,x,count));
                        }

                    }



                }
            }
        }
//        ArrayDeque<Point> q = new ArrayDeque<>();
//        q.add(new Point(startY, startX, 0));
//
//        int max = 0;
//        boolean success = false;
//        while(!q.isEmpty()) {
//            final Point now = q.poll();
//
//            if(map[now.y][now.x]=='O') {
//                max = Math.max(max, now.count);
//                success = true;
//            }
//
//            for(int i= 0;i<3;i++) {
//                int y = dy[i] + now.y;
//                int x = dx[i] + now.x;
//
//                if(y>=0&&x>=0&&y<N&&x<M&&map[y][x]!='#'&&visited[y][x]<now.count+1) {
//                    int count = now.count;
//                    if(map[y][x]=='C') {
//                        count++;
//                    }
//                    visited[y][x] = count;
//                    q.add(new Point(y,x,count));
//                }
//
//            }
//
//        }

//        for(int i= 0;i<N;i++) {
//            System.out.println();
//            for(int j= 0;j<M;j++) {
//                System.out.print(visited[i][j]+" ");
//            }
//
//        }
        if(!success) {
            System.out.println(-1);
            return;
        }

        System.out.println(max);


    }
}
