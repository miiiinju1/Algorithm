import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] visited = new int[5][5];
static boolean success = false;

    static void dfs(int y, int x,int move,int count)
    {

        if(y >= 0 && x >= 0 && x < 5 && y < 5&&visited[y][x]!=1&&move<=3)
        {
            int c= count;
            if(map[y][x]>=1)
                c+=map[y][x];
            if(c>=2)
                success=true;
            visited[y][x]=1;
            for(int i= 0;i<4;i++)
            {
                dfs(y+dy[i],x+dx[i],move+1,c);
            }
            visited[y][x]=0;
        }

    }
    static int[][] map = new int[5][5];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        for(int i= 0;i<5;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j =0;j<5;j++)
            {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == -1)
                    visited[i][j] = 1;
                map[i][j] = temp;
            }
        }
        st = new StringTokenizer(br.readLine());
        int init_y = Integer.parseInt(st.nextToken());
        int init_x = Integer.parseInt(st.nextToken());


        dfs(init_y,init_x,0,0);


        if(success)
            System.out.println(1);
        else
            System.out.println(0);




    }
}
