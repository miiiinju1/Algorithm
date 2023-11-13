import java.util.*;

class Solution {
    
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    
    static class Check {
        int count;
        int y,x;
        int inity,initx;
        boolean possible;
        public Check(int y, int x, int count,boolean possible,int inity,int initx) {
            this.y = y;
            this.x=x;
            this.count = count;
            this.possible = possible;
            this.inity = inity;
            this.initx = initx;
        }
        
        
    }
    public int[] solution(String[][] places) {
        
        
         int[] answer = new int[places[0].length];
        int index= 0;
        
        for (String[] tc : places) {
            
            String[][] map = new String[5][5];
            
            for(int i= 0;i<5;i++) {
                 map[i] = tc[i].split("");
            }
            
            Queue<Check> q = new LinkedList<Check>();
            
            int result = 1;
            
            for(int i= 0;i<5;i++) {
                for(int j =0;j<5;j++) {
                
                    if(map[i][j].equals("P"))
                        q.add(new Check(i,j,0,false,i,j));
                }
            }
                
              
                
            while(!q.isEmpty()) {
                Check now = q.poll();
                if(now.count!=0&&map[now.y][now.x].equals("P")) {
                    
                    if(now.count==2&&now.possible) {
                        continue;
                    }
                    else {
                        result = 0;
                        break;
                    }
                }
                if(map[now.y][now.x].equals("X")&&now.count<2) {
                    now.possible = true;
                }
                if(now.count<2) {
                    for(int d = 0;d<4;d++) {
                    
                        int y= now.y+dy[d];
                        int x= now.x+dx[d];

                        if(y>=0&&y<5&&x>=0&&x<5&&!(y==now.inity&&x==now.initx)) {

                            q.add(new Check(y,x,now.count+1,now.possible,now.inity,now.initx));
                        }
                    }
                }

                
                    
                    
                    
                }
                
                
            answer[index++] = result;
             
        }
        
        
        
        
       
        return answer;
    }
}