class Solution {
    
    static void rotate (int[][] key) {
        int len = key[0].length-1;
        
        int[][] temp = new int[len+1][len+1];
        
        for(int i= 0;i<=len;i++) {
            for(int j= 0;j<=len;j++) {
                temp[i][j] = key[j][len-i];
            }
            
        }
        for(int i= 0;i<=len;i++) {
            for(int j= 0;j<=len;j++) {
                key[i][j] = temp[i][j];
            }
            
        }
        
        //0,0 0,1 0,2
        //1,0 1,1 1,2
        //2,0 2,1 2,2
        
        
        //0,2 1,2 2,2
        //0,1 1,1 2,1
        //0,0 1,0 2,0
        
    }
    
    
    
    static boolean match(int[][] key) {
        
        for(int i= 0;i<mapLen-left;i++) {
            for(int j= 0;j<mapLen-left;j++) {
                 if(check(i,j,key))
                     return true;
                for(int r= 1;r<4;r++) {
                    rotate(key);
                    if(check(i,j,key))
                       return true;
                } 
            }
        }
        return false;
    }
    
    static boolean check(int x, int y,int[][] key) {
        int count = 0;
        for(int i = 0;i<M;i++) {
            // System.out.println();
            for(int j= 0;j<M;j++) {
                // System.out.print(map[i+x][j+y]+""+key[i][j]+" ");
                if(i+x>=left&&j+y>=left&&i+x<right&&j+y<right){
                if((key[i][j]^map[i+x][j+y])==1)   {
                    if(map[i+x][j+y]==0)
                    count++;
                    continue;
                }
                else
                    return false;
                }
            }
        }
        // System.out.println(count);
        
        if(checkCount==count)
            return true;
        return false;
    }
    
    
    static int M, N,left,right,mapLen,checkCount=0;
    static int[][] map;
    public boolean solution(int[][] key, int[][] lock) {
        M = key[0].length;//3
        N = lock[0].length;//3
        mapLen = lock[0].length+ 2*(M-1); //7
        //2*(M-1)+N이 돼야함
        map = new int[mapLen][mapLen]; 
        left = M-1; //2
        right = mapLen-left; //
        
        
        
        for(int i = 0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(lock[i][j]==0)
                     checkCount++;
                map[i+left][j+left] = lock[i][j];
            }
        }
        // for(int i= 0;i<mapLen;i++) {
        //     System.out.println();
        //     for(int j= 0;j<mapLen;j++)
        //         System.out.print(map[i][j]+" ");
        // }
        
        

        
        
        
        boolean answer = match(key);
        return answer;
        
    }
}

//xor연산 11 X, 00 X, 01 or 10