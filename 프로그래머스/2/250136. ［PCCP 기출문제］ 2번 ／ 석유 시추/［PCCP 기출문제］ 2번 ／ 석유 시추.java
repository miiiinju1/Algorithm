import java.util.*;
class Solution {
    static class Point {
        int y, x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int n,m;
    
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,-1,0,1};
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        
        boolean[][] visited = new boolean[n][m];
        int[][] map = new int[n][m];
        int[][] distinct = new int[n][m];
        int unique = 1;
        for(int i= 0;i<n;i++) {
            
            for(int j= 0;j<m;j++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    ArrayDeque<Point> q = new ArrayDeque<>();
                    ArrayDeque<Point> visit = new ArrayDeque<>();
                    int count = 1;
                    visit.add(new Point(i,j));
                    visited[i][j] = true;
                    q.add(new Point(i,j));
                    
                    while(!q.isEmpty()) {
                        Point now = q.poll();
                        
                        for(int d = 0;d<4;d++) {
                            int y = dy[d] + now.y;
                            int x = dx[d] + now.x;
                            if(y>=0&&x>=0&&y<n&&x<m&&!visited[y][x] && land[y][x] == 1) {
                                visited[y][x]=true;
                                count++;
                                Point p = new Point(y,x);
                                q.add(p);
                                visit.add(p);
                            }
                        }
                        
                    }
                    
                    for(Point p : visit) {
                        map[p.y][p.x] = count;
                        distinct[p.y][p.x] = unique;
                        
                    }
                    unique++;
                    
                    
                }
                
                
            }
        }
        //1 석유 0 빈땅
        int max =0;
        for(int j= 0;j<m;j++) {
            HashSet<Integer> v = new HashSet<>();
            int sum =0;
            for(int i= 0;i<n;i++) {
                if(distinct[i][j] !=0 ) {
                    if(!v.contains(distinct[i][j])) {
                        v.add(distinct[i][j]);
                        sum+= map[i][j];
                    }
                }
               
            }
            max = Math.max(max, sum);
            sum = 0;
        }
        
        
        return max;
    }
}