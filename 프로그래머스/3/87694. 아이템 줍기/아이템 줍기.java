import java.util.*;
class Solution {
    public static int[][] map = new int[102][102];
     public static void draw(int[] rectangle)
    {
        
        //아래쪽 테두리를 1로
        for(int j = rectangle[0]*2;j<=2*rectangle[2];j++)
            {
                if(map[rectangle[1]*2][j]==-1)//초기화 안 됐으면
                    map[rectangle[1]*2][j]=1;
            if(map[2*rectangle[3]][j]==-1)//초기화 안 됐으면
                    map[2*rectangle[3]][j]=1;
                
        }
      

        //위쪽 테두리를 1로
        


        
        for(int i = rectangle[1]*2+1;i<2*rectangle[3];i++)
        {
            
            //처음 끝
            if(map[i][rectangle[0]*2]==-1) {
              
            	map[i][rectangle[0]*2]=1;
            }
         
            
            if(map[i][2*rectangle[2]]==-1)
            {
            	map[i][rectangle[2]*2]=1;
            }
            
      
            
            
            for(int j = rectangle[0]*2+1;j<2*rectangle[2];j++)
            {
                
               //완전 내부
                map[i][j]=0;
       
            }
        
        }
        
        
    }
    static class Point{
        
        int y, x, count;
        
        public Point(int y, int x, int count)
        {
            this.y = y;
            this.x = x;
            this.count = count;
            
        }
        
    }
    
    public static boolean[][] visited = new boolean[102][102];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int i = 0;i<102;i++)
            Arrays.fill(map[i],-1);
        
        for(int i=0;i<rectangle.length;i++)
            draw(rectangle[i]);
        
        //시작점부터 특정점까지 bfs
        
        Queue<Point> queue = new LinkedList<Point>();
        visited[characterY*2][characterX*2]=true;
        for(int i= 0;i<102;i++){
            for(int j =0;j<102;j++){
                if(map[i][j]!=1)//테두리가 아니면 
                    visited[i][j]=true;//방문처리          
            }
        
        }
        //y좌표 하나 당 visited가 true깐
        
        
        
        
        queue.add(new Point(characterY*2, 2*characterX, 0));
        int destY = itemY*2;
        int destX = itemX*2;
        int[] dy = {0,0,-1,1};
        int[] dx = {-1,1,0,0};
       
        while(!queue.isEmpty())
        {
            Point now = queue.poll();
            
            for(int i= 0;i<4;i++)
            {
            int Y = now.y+dy[i];
            int X = now.x+dx[i];
            if(Y==destY&&X==destX)
            {
                return (now.count+1)/2;
                
            }
            else if(Y>=0&&X>=0&&Y<102&&X<102&&!visited[Y][X])
            {
                queue.add(new Point(Y,X,now.count+1));
                visited[Y][X]=true;
                
            }
            
            
            }
        }
        
        return 0;
        
        
        
        
        
        
        
    }
}