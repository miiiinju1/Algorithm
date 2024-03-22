class Solution {
    static int[] dy = {0,-1,0,1,0};
    static int[] dx = {0,0,-1,0,1};
    static int[] selected;
    
    static int check(int[][] temp) {
        int n = temp.length;
        int[][] map = new int[n][n];
        for(int i= 0;i<n;i++) {
            for(int j= 0;j<n;j++) {
                map[i][j] = temp[i][j];
            }
        }
        
        
        //맨 윗줄 세팅
        int count = 0;
        for(int j = 0;j<n;j++) {
            int c = 0;
            while(c<selected[j]) {
                c++;
                count++;
                for(int d= 0;d<5;d++) {
                    int y = dy[d];
                    int x = dx[d]+j;

                    if(y>=0&&x>=0&&y<n&&x<n) {
                        map[y][x] = (map[y][x]+1)%4;
                    }
                }
            }
            
        }
        
        //맨 윗줄에 따라 아래줄 세팅
        for(int i = 1;i<n;i++) {
            for(int j= 0;j<n;j++) {
                while(map[i-1][j]!=0) {
                    count++;
                    for(int d = 0;d < 5;d++) {
                        int y = dy[d]+i;
                        int x = dx[d]+j;
                        
                        if(y>=0&&x>=0&&y<n&&x<n) {
                            map[y][x] = (map[y][x]+1)%4;
                        }
                    }
                }       
            }
        }
        
        for(int i= 0;i<n;i++) {
            for(int j= 0;j<n;j++) {
                if(map[i][j]!=0) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        
        return count;   
    }
    
    static int min = Integer.MAX_VALUE;
    static void combination(int depth, int[][] map) {
        if(depth == selected.length) {

            int result = check(map);
            min = Math.min(min,result);

            
            return;
        }
        for(int i= 0;i<4;i++) {
            selected[depth] = i;
            combination(depth+1, map);
        }
    }
    
    
    public int solution(int[][] clockHands) {
        int n = clockHands.length;
        selected = new int[n];
    
        combination(0, clockHands);
       
        return min;
    }
}