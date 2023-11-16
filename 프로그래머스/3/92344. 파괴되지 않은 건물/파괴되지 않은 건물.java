class Solution {
    static int[][] board;
    static void attack(int[] skill) {
        int r1 = skill[1];
        int c1 = skill[2];
        int r2 = skill[3];
        int c2 = skill[4];
        int degree = skill[5];
        board[r1][c1] -= degree;
        r2 +=1;
        c2 +=1;
        if(c2<board[0].length){ //시작 x좌표가 넘지 않는 격ㅇ우
            board[r1][c2] += degree;
        }
        if(r2<board.length) 
            board[r2][c1] +=degree;

        if((c2<board[0].length)&&(r2<board.length))
            board[r2][c2]-=degree;




    }
    static void heal (int[] skill) {
        int r1 = skill[1];
        int c1 = skill[2];
        int r2 = skill[3];
        int c2 = skill[4];
        int degree = skill[5];
        board[r1][c1] += degree;
        r2 +=1;
        c2 +=1;
        if(c2<board[0].length){ //시작 x좌표가 넘지 않는 격ㅇ우
            board[r1][c2] -= degree;

        }
        if(r2<board.length) 
            board[r2][c1] -=degree;
        if((c2<board[0].length)&&(r2<board.length))
            board[r2][c2]+=degree;



    }
    public int solution(int[][] b, int[][] skills) {
        board  = new int[b.length][b[0].length];
        for(int[] skill : skills) {
            if(skill[0]==1) {//적의 공격
                attack(skill);
            }
            else {
                heal(skill);
            }
        }
        int answer=0;


        for(int i=0;i<b.length;i++) {
            for(int j= 1;j<b[0].length;j++) {
                board[i][j] += board[i][j-1]; 
            }
        } 
        for(int j= 0;j<b[0].length;j++) {
            for(int i=1;i<b.length;i++) {
                board[i][j] += board[i-1][j]; 
            }
        }
        for(int i=0;i<b.length;i++) {
            for(int j= 0;j<b[0].length;j++) {
                b[i][j]+=board[i][j];
                if(b[i][j]>0)
                    answer++;
            }
        }


        return answer;
    }
}
