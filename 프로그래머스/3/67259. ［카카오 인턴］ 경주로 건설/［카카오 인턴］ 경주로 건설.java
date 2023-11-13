import java.util.*;
class Solution {
    static class Point { 
        int y,x, value, before;
        
        public Point (int y, int x, int value,int before) {
            this.y = y;
            this.x = x;
            this.value = value;
            this.before = before;
            
        }
    }
                        // 위 오른쪽 아래쪽 왼쪽
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    
    static int[][][] weight;
    public int solution(int[][] board) {
        
        int height = board.length;
        int width = board[0].length;
        
        weight = new int[4][height][width];
        
        for(int i= 0;i<height;i++) {
            for(int d = 0;d<4;d++)
                Arrays.fill(weight[d][i],Integer.MAX_VALUE);
        }
    
        Queue<Point> q = new LinkedList<>();
        weight[0][0][0]=0;
        weight[1][0][0]=0;
        weight[2][0][0]=0;
        weight[3][0][0]=0;

        if(board[0][1]==0) {
            q.add(new Point(0,1,100,1));
            weight[1][0][1]=100;
        }
        if(board[1][0]==0){
            q.add(new Point(1,0,100,2));
            weight[2][1][0]=100;
            
        }
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            

            
            for(int i= 0;i<4;i++) {
                int y = now.y+dy[i];
                int x = now.x+dx[i];
                
                
                if(y>=0&&x>=0&&y<height&&x<width&&board[y][x]!=1) {
                    //이전에 오른쪽이였는데 방
                    int value = now.value+100; 

                    if(now.before!=i) {
                        value += 500;
                    }
                    
                    if(weight[i][y][x]>=value) {
                        // System.out.println(y+" "+x+" "+value);
                        weight[i][y][x]=value;
                        q.add(new Point(y,x,value,i));
                  
                    }
                }
                
                
            }
            
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<4;i++) {
            if(min>weight[i][height-1][width-1])
                min = weight[i][height-1][width-1];
            
        }

        
      
        return min;
    }
}