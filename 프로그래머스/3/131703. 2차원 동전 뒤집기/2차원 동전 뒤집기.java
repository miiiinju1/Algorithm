class Solution {
    static void arrayCopy(int[][] from, int[][] to) {
        for(int i= 0;i<from.length;i++) {
            for(int j= 0;j<from[0].length;j++) {
                to[i][j] = from[i][j];
            }
        }
    }
    static boolean match(int[][] from, int[][] to) {
        for(int i= 0;i<from.length;i++) {
            for(int j= 0;j<from[0].length;j++) {
                if(to[i][j]!=from[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    static void print(int[][] from) {
        for(int i= 0;i<from.length;i++) {
            for(int j= 0;j<from[0].length;j++) {
                System.out.print(from[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    static void swapCol(int x, int[][] temp) {
        for(int i= 0;i<temp.length;i++) {
            temp[i][x] = (temp[i][x]+1)%2;
        }
    }
    static void swapRow(int y, int[][] temp) {
        for(int i= 0;i<temp[0].length;i++) {
            temp[y][i] = (temp[y][i]+1)%2;
        }
    }
    
    public int solution(int[][] beginning, int[][] target) {
        int ylen = beginning.length;
        int xlen = beginning[0].length;
        int max = 1<<(ylen+xlen+1);
        int[][] temp = new int[ylen][xlen];
        
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<max;i++) {
            arrayCopy(beginning, temp);
            int count = 0;
            int j= 0;
            for (; j < xlen; j++) {
                if ((i & (1 << j)) > 0) {
                    //열 변경
                    //j행을 뒤집기
                    count++;
                    swapCol(j,temp);
                }
            }
            for (; j < ylen+xlen; j++) {
                if ((i & (1 << j)) > 0) {
                    //행 변경
                    //j-len 행을 뒤집기
                    count++;
                    swapRow(j-xlen,temp);
                }
            }
            if(match(temp, target)) {
                min = Math.min(min, count);
            }
        }
        if(min==Integer.MAX_VALUE) {
            min = -1;
        }
        return min;
    }
}

// 완전 탐색을 하게 되면

/*





*/