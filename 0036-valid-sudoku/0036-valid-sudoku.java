class Solution {
    static char[][] board;
    public boolean isValidSudoku(char[][] b) {
        this.board = b;

        for(int i = 0;i<9;i++) {
            for(int j= 0;j<9;j++) {
                if(board[i][j]!='.') {
                    if(!validate(i,j)) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    static boolean validate(int y, int x) {
        // sub-box validate
        int heightIdx = (y/3)*3;
        int widthIdx = (x/3)*3;

        //heightIdx, widthIdx 기준으로 3개씩

        char now = board[y][x];
    

        for(int i = heightIdx;i<heightIdx+3;i++) {
            for(int j= widthIdx;j<widthIdx + 3; j++) {
                if(i == y && j == x) continue;
                

                if(board[i][j] == now) {
                    return false;
                }
            }
        }

        // 행 validate
        // y는 유지하고, 

        for(int i = 0;i<9;i++) {
            if(i== x) continue;

            if(board[y][i]==now) {
                return false;
            }
        }

        // 열 validate
        for(int i = 0;i<9;i++) {
            if(i == y) continue;

            if(board[i][x] == now) {
                return false;
            }
        }
        return true;
    }
}

// ( 9 * 9 * 9 ) * 81 = 9^5