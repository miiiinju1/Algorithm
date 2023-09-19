import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {0,-1,0,1};
    static int[] dx = {1,0,-1,0};
    static int[][] map ;
    static int N,M,K;
 
    static class Point{
        int y,x;
        public Point(int y,int x)
        {
            this.y=y;
            this.x =x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int init_M = M;
       map = new int[N][N];
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j =0;j<N;j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count =0;
        Queue<Point> q =null;

        for(int i =0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(M>=0&&map[i][j]==0) {
                    q = new LinkedList<>();
                    q.add(new Point(i,j));
                    count =0;
                    M--;


                    while(!q.isEmpty()) {
                        Point now =q.poll();
                        if(count<K&&M>0&&now.y>=0&&now.x>=0&&now.y<N&&now.x<N&&map[now.y][now.x]==0)
                        {
                            map[now.y][now.x]=1;
                            count++;
                            if(M==0)
                            {
                                break;
                            }
                            if(count==K)
                            {
                                count = 0;
                                break;
                            }
                            for(int z=0;z<4;z++)
                            {
                                q.add(new Point(now.y+dy[z],now.x+dx[z]));
                            }
                        }

                    }




                }
            }
        }
        int result =N*N;
        for(int i=0;i<N;i++)
        {
            for (int j=0;j<N;j++)
            {
                result-=map[i][j];
            }
        }

        if(result==0&&M!=init_M) {
            System.out.println("POSSIBLE");
            System.out.println(M);
        }
        else
            System.out.println("IMPOSSIBLE");

    }
}
