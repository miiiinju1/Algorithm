import java.util.*;
class Solution {
    class Point
    {
        int y,x,count;
        
        public Point(int y, int x, int count)
        {
            this.y=y;
            this.x=x;
            this.count = count;
        }
        
        
        
        
    }
    public int solution(int[][] maps) {
        
        
      
        
        int n=maps.length,m=maps[0].length;
        boolean[][] visited = new boolean[n][m];
        visited[0][0]=true; 
        for(int i= 0;i<n;i++)
            for(int j= 0;j<m;j++)
                if(maps[i][j]==0)
                    visited[i][j]=true;
        
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(0,0,1));
        int[] dy ={0,1,0,-1};
        int[] dx = {1,0,-1,0};
        while(!queue.isEmpty())
        {
            Point now = queue.poll();
        for(int i= 0;i<4;i++)
        {
            int Y = now.y+dy[i];
            int X = now.x+dx[i];
            
            if(Y>=0&&X>=0&&Y<n&&X<m&&!visited[Y][X])
            {
                
                queue.add(new Point(Y,X,now.count+1));
                visited[Y][X]=true;
                if(visited[n-1][m-1])
                    return now.count+1;
            }
            
            
            
        }
        }
        
        return -1;
    }
}