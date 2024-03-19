import java.util.*;
class Solution {
    static class Phase {
        int y, x, count;
        int[][] board;
        HashSet<Integer> visited;
        public Phase(int y, int x, int count, int[][] board, HashSet<Integer> visited) {
            this.visited = new HashSet<>(visited);
            this.board = new int[4][4];
            this.y =y;
            this.x =x ;
            this.count = count;
            for(int i= 0;i<4;i++) {
                for(int j= 0;j<4;j++) {
                    this.board[i][j] = board[i][j];
                }
            }
            
        }
        
        public String toString() {
            return y+" "+x+" "+count+" "+visited;
            
        }
        
        
        
        
    }
    static class Point {
        int y, x, depth;
        public Point(int y, int x, int depth) {
            this.y = y;
            this.x =x;
            this.depth = depth;
        }
        public String toString() {
            return y+" "+x+ " "+depth;
        }
    }
    
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,-1,0,1};
    
    static boolean isValid(int y, int x) {
        return y>=0&&x>=0&&y<4&&x<4;
    }
    
    static int getDistance(int i, int j, int targetY, int targetX, int[][] board) {
        int[][] visited = new int[4][4];
        int start = board[i][j];
        for(int d= 0;d<4;d++) {
            Arrays.fill(visited[d], Integer.MAX_VALUE);
        }
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(i,j,0));
        visited[i][j] = 1;
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            if(now.y==targetY && now.x == targetX) {
                return now.depth;
            }
            for(int d= 0;d<4;d++) {
                int y = now.y;
                int x = now.x;
                while(true) {
                    y += dy[d];
                    x += dx[d];
                    if(!isValid(y,x)) {
                        y-= dy[d];
                        x-= dx[d];
                        break;
                    }
                    if(board[y][x]!=0) {
                        break;                                    
                    }
                }
                if(visited[y][x]>now.depth+1) {
                    visited[y][x] = now.depth+1;
                    q.add(new Point(y,x,now.depth+1));
                }
            }
            for(int d= 0;d<4;d++) {
                int y = dy[d] + now.y;
                int x = dx[d] + now.x;
                if(isValid(y,x) && visited[y][x] > now.depth+1 ) {
                    visited[y][x] = now.depth+1;
                    q.add(new Point(y,x,now.depth+1));
                }
            }
        }
        return 0;
        
    }
    public int solution(int[][] b, int r, int c) {
        
        
        HashMap<Integer, ArrayList<Point>> map = new HashMap<>();
        
        for(int i=0 ;i<4;i++) {
            for(int j= 0;j<4;j++) {
                if(b[i][j] != 0) {
                    map.computeIfAbsent(b[i][j], k-> new ArrayList<>());
                    map.get(b[i][j]).add(new Point(i,j,0));
                    
                }
            }
        }
        
        //현재 시점에서 찾아서 bfs돌리면 될듯? 대신 bfs할 때, 1', 1'', 2', 2'', 3', 3''
        
        ArrayDeque<Phase> q = new ArrayDeque<>();
        q.add(new Phase(r,c,0,b, new HashSet<>()));
        
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            
            Phase now = q.poll();
            
            if(now.visited.size()==map.size()) {
                min = Math.min(min,now.count);
                continue;
            }
            
            for(Integer key : map.keySet()) {
                if(!now.visited.contains(key)) {
                    // System.out.println("key: "+key);
                    Point p1 = map.get(key).get(0);
                    Point p2 = map.get(key).get(1);
                    
                    //현재 위치로부터 key에 Point로 p1까지 해당하는 값까지 거리 구하기
                    
                    int count1 = getDistance(now.y, now.x, p1.y, p1.x, now.board);
                    int short1 = getDistance(p1.y,p1.x, p2.y,p2.x, now.board)+2;
                    
                    int count2 = getDistance(now.y, now.x, p2.y, p2.x, now.board);
                    int short2 = getDistance(p2.y,p2.x, p1.y,p1.x, now.board)+2;

                    now.board[p1.y][p1.x] = 0;
                    now.board[p2.y][p2.x] = 0;
                    now.visited.add(key);
                    
                    // System.out.println(count1+" "+count2);
                    // System.out.println(shortest);
                    
                    q.add(new Phase(p2.y,p2.x,now.count+count1+short1,now.board,now.visited));
                    q.add(new Phase(p1.y,p1.x,now.count+count2+short2,now.board,now.visited));

                    now.visited.remove(key);
                    now.board[p1.y][p1.x] = key;
                    now.board[p2.y][p2.x] = key;
                }
            }
        }
        
        return min;
    }
}