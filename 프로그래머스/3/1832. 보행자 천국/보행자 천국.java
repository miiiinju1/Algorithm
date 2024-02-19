class Solution {
    int MOD = 20170805;
    
    int[] dy = {1,0};
    int[] dx = {0,1};
    public int solution(int m, int n, int[][] cityMap) {
        
        int[][][]map = new int[2][m][n];
    
        for(int j= 0;j<n;j++) {
            if(cityMap[0][j]!=1) {
               map[1][0][j] = 1;
            }
            else {
                break;
            }
        }
          for(int i = 1;i<m;i++) {
            if(cityMap[i][0]!=1) {
                map[0][i][0] = 1;
            } 
              else {
                  break;
              }
          }


        for(int i = 1;i<m;i++) {
            for(int j= 1;j<n;j++) {
                switch (cityMap[i][j-1] ) {
                    case 0:
                        map[1][i][j] = (map[1][i][j]+ (map[0][i][j-1]+map[1][i][j-1]))%MOD;
                        break;
                        
                    case 1:
                        break;
                    case 2:
                        map[1][i][j] = (map[1][i][j-1]+map[1][i][j])%MOD;
                        break;
                        
                }
                
                switch (cityMap[i-1][j] ) {
                    case 0:
                        map[0][i][j] = (map[0][i][j]+ (map[0][i-1][j]+map[1][i-1][j]))%MOD;
                        break;
                        
                    case 1:
                        break;
                        
                    case 2:
                        map[0][i][j] = (map[0][i][j]+map[0][i-1][j]) %MOD;
                        break;
                }
            }
            
        }
        
        
//         for(int i = 0;i<m;i++) {
//             for(int j= 0;j<n;j++) {
//                 System.out.print(map[0][i][j]+" ");
//             }
//             System.out.println();
//         }
        
//         for(int i = 0;i<m;i++) {
//             for(int j= 0;j<n;j++) {
//                 System.out.print(map[1][i][j]+" ");
//             }
//             System.out.println();
//         }
        
        
        
        
        
        int answer = (map[0][m-1][n-1]+map[1][m-1][n-1])%MOD;
        return answer;
    }
}


