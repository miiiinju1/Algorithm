class Solution {
    static int[][] map;
    
    static int y=-1,x=0,value = 1,n = 0;
    
    
    static void down() {
        y++;
        
        for(;y<n;y++) {
            if(map[y][x]==0) 
                map[y][x] = value++;
            else {
                break;
            }
        }
        y--;
    }
    static void right() {
        x++;
        for(;x<n;x++) {
            if(map[y][x]==0) 
                map[y][x] = value++;
            else {
                break;
            }
        }
        x--;
    }
    
    static void up() {
        y--;
        x--;
        for(;x>=0 && y>=0;y--,x--) {
            if(y<x) {
                break;
            }
            if(map[y][x]==0) 
                map[y][x] = value++;
            else {
                break;
            }
        }
        y++;
        x++;
    }
    
    
    
    
    public int[] solution(int N) {
        int all = 1;
        for(int i =2;i<=N;i++) {
            all+=i;
        }
//         int[] answer = new int[all];
        
        
        // 1 2 3 1 1 1 1 3 2 
        // 0 1 3 6 7 8 9 5 2 4
//         while(true) {
            
            
//         }
        
        
        n = N;
        map = new int[n][n];
        
        while(value<=all) {
            down();
        right();
        up();
        }
      
       
        int index = 0;
        
        int[] answer = new int[all];
         for(int i= 0;i<n;i++) {
            for(int j= 0;j<n;j++) {
                if(i<j) {
                    break;
                }
                answer[index++] = map[i][j];
            }
        }
        
        return answer;
    }
}