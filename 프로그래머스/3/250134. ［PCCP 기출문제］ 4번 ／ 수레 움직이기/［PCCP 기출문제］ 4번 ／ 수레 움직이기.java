import java.util.*;

class Phase {
    boolean[][] visitedA;
    boolean[][] visitedB;
    int yA,xA;
    int yB,xB;
    int depth;
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Phase{\n");
         for(int i =0;i<visitedA.length;i++) {
            for(int j= 0;j<visitedA[0].length;j++) {
                sb.append(visitedA[i][j]+" ");
            
            }
             sb.append("\n");
        }
        sb.append("---\n");
        for(int i =0;i<visitedA.length;i++) {
            for(int j= 0;j<visitedA[0].length;j++) {
                  sb.append(visitedB[i][j]+" ");
            }
             sb.append("\n");
        }
        sb.append(", yA=").append(yA);
        sb.append(", xA=").append(xA);
        sb.append(", yB=").append(yB);
        sb.append(", xB=").append(xB);
        sb.append(", depth=").append(depth);
        sb.append('}');
        return sb.toString();
    }
    
    public Phase(int yA,int xA, int yB, int xB, int depth, boolean[][] visitedA, boolean[][] visitedB) {
        
        this.yA = yA;
        this.yB = yB;
        this.xA = xA;
        this.xB = xB;
        this.depth = depth;
        
        this.visitedA = new boolean[visitedA.length][visitedA[0].length];
        this.visitedB = new boolean[visitedA.length][visitedA[0].length];
        for(int i =0;i<visitedA.length;i++) {
            for(int j= 0;j<visitedA[0].length;j++) {
                this.visitedA[i][j] = visitedA[i][j];
                this.visitedB[i][j] = visitedB[i][j];
            }
        }
        
        
    }
    
}

class Solution {
    
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,-1,0,1};
    
    static int n,m;
    
    static boolean isValid(int y, int x) {
        return y>=0&&x>=0&&y<n&&x<m;
    }
    public int solution(int[][] maze) {
        int yA=-1, xA = -1, yB = -1, xB = -1;
        int destA_Y= -1, destA_X = -1, destB_Y = -1, destB_X = -1;
        n = maze.length;
        m = maze[0].length;
        boolean[][] visitedA = new boolean[n][m];
        boolean[][] visitedB = new boolean[n][m];
    
        for(int i= 0;i<n;i++) {
            for(int j= 0;j<m;j++) {
                switch(maze[i][j]) {
                    case 0:
                        break;
                    case 1:
                        yA = i;
                        xA = j;
                        visitedA[i][j] = true;
                        break;
                    case 2:
                        yB = i;
                        xB = j;
                        visitedB[i][j] = true;
                        break;
                    case 3:
                        destA_Y = i;
                        destA_X = j;
                        break;
                    case 4:
                        destB_Y = i;
                        destB_X = j;
                        break;
                    case 5:
                        visitedA[i][j] = true;
                        visitedB[i][j] = true;
                        break;
                        
                }
        
            
            }
        }
        
        ArrayDeque<Phase> q = new ArrayDeque<>();
        q.add(new Phase(yA,xA, yB, xB, 0, visitedA, visitedB));
        
        while(!q.isEmpty()) {
            
            Phase now = q.poll();
            
            int aY = -1, aX=-1, bY=-1, bX=-1;
            boolean A = false, B = false;
            
            for(int d =0;d<4;d++) {
                A = false;
                aY = now.yA + dy[d];
                aX = now.xA + dx[d];
                if(now.yA == destA_Y && now.xA == destA_X) {
                    aY = destA_Y;
                    aX = destA_X;
                    A = true;
                }
                if(!A &&isValid(aY,aX) && !now.visitedA[aY][aX]) {
                    A = true;
                    
                }
                if(A) {
                    for(int dd =0;dd<4;dd++) {
                        B = false;
                        bY = now.yB + dy[dd];
                        bX = now.xB + dx[dd];
                        // 도착지면 안 움직이는 로직
                        if(now.yB == destB_Y && now.xB == destB_X) {
                            bY = destB_Y;
                            bX = destB_X;
                            B = true;
                        }
                        if(!B && isValid(bY,bX) && !now.visitedB[bY][bX]) {
                            if(bY == aY && bX == aX) {
                                B = false;
                            }
                            else {
                                B = true;
                            }
                        }
                        
                        if((aY==now.yB && aX == now.xB) && (bY==now.yA && bX == now.xA)) {
                            B = false;
                        }   
                        if(A && B) {
                            if(aY == destA_Y && aX == destA_X && bY == destB_Y && bX == destB_X) {
                                return now.depth+1;
                            }
                            now.visitedA[aY][aX] = true;
                            now.visitedB[bY][bX] = true;
                            q.add(new Phase(aY, aX, bY, bX, now.depth+1, now.visitedA, now.visitedB));
                            
                            // if(!(now.yA == aY && now.xA == aX))
                            now.visitedA[aY][aX] = false;
                            // if(!(now.yB == bY && now.xB == bX))
                            now.visitedB[bY][bX] = false;

                        }        
                    }
                }
            }
        }
        return 0;
    }
}