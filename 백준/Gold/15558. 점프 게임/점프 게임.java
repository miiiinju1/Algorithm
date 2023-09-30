import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int y,x,depth;

        public Point(int y, int x, int depth) {
            this.y=y;
            this.x=x;
            this.depth=depth;
        }
        
    }
    static int[][] map;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        map = new int[2][N];

        for(int i=0;i<2;i++)
        {
            String str = br.readLine();
            for(int j= 0;j<N;j++){

                map[i][j] = str.charAt(j)-48;
            }


        }
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0));


        while (!q.isEmpty()) {
            Point now = q.poll();

            if(now.x<now.depth)
                continue;
            if(now.x>=N) {
                System.out.println(1);
                return;
            }
            if(map[now.y][now.x]==0)
                continue;
            map[now.y][now.x]=0;
            int y= now.y;
            int x = now.x+1;


            if(x>=0)
                q.add(new Point(y, x, now.depth + 1));

            x = now.x-1;

            if(x>=0)
                q.add(new Point(y, x, now.depth + 1));

            y= (now.y+1)%2;
            x = now.x+k;

            if(x>=0)
                q.add(new Point(y, x, now.depth + 1));

        }
        System.out.println(0);

    }
}
