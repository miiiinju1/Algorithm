
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static String str;
    static class Point{
        int y,x,depth,prev;

        public Point(int y, int x, int depth,int prev) {
            this.y = y;
            this.x = x;
            this.depth = depth;
            this.prev = prev;
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for(int i=0;i<N;i++)
        {
            String temp = br.readLine();
            for(int j= 0;j<M;j++)
            {
                map[i][j]=temp.charAt(j);
            }
        }
        Queue<Point> q = new LinkedList<>();


        for(int i=0;i<N;i++)
        {
            for(int j =0;j<M;j++)
            {
                if (map[i][j] == str.charAt(0)) {
                    for(int x =0;x<8;x++)
                        q.add(new Point(i, j, 0,x));
                }
            }
        }
        int[] dy = {1,1,1,0,-1,-1,-1,0};
        int[] dx = {-1,0,1,1,1,0,-1,-1};
        int len = str.length()-1;
        while (!q.isEmpty()) {
            Point now = q.poll();
            if(str.charAt(now.depth)!=map[now.y][now.x])
            {
                continue;
            }
            if(now.depth == len) {
                System.out.println(1);
                return ;
            }

            int y = now.y+dy[now.prev];
            int x = now.x+dx[now.prev];

            if(y>=0&&x>=0&&y<N&&x<M)
            {
                q.add(new Point(y, x, now.depth + 1, now.prev));
            }
            

        }
        System.out.println(0);

    }
}
