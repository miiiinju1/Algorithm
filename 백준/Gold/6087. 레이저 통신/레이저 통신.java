import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Phase implements Comparable<Phase> {
        int y, x;
        int dir, turnCount;

        public Phase(int y, int x, int dir, int turnCount) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.turnCount = turnCount;
        }

        @Override
        public int compareTo(Phase o) {
            return Integer.compare(this.turnCount, o.turnCount);
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static boolean isValid(int y, int x) {
        return y>=0 && x>=0 && y<H && x<W;
    }
    static int W,H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         W = Integer.parseInt(st.nextToken());
         H = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];
        int startY = -1, startX = -1, endY = -1, endX = -1;
        for (int i = 0; i < H; i++) {
            final String str = br.readLine();

            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'C') {
                    if (startY == -1) {
                        startY = i;
                        startX = j;
                    } else {
                        endY = i;
                        endX = j;
                    }
                }

            }
        }

        PriorityQueue<Phase> pq = new PriorityQueue<>();
        int[][] visited = new int[H][W];
        for(int i= 0;i<H;i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[startY][startX] = 0;
        for(int z = 0;z<4;z++) {
            int ny = dy[z] + startY;
            int nx = dx[z] + startX;
            if(isValid(ny,nx)&& map[ny][nx] != '*') {
                visited[ny][nx] = 0;

                pq.add(new Phase(ny, nx, z, 0));
            }
        }

        while(!pq.isEmpty()) {
            final Phase now = pq.poll();


            if(now.y==endY && now.x ==endX) {

                System.out.println(now.turnCount);
                return;
//                continue;
            }

            for (int d = 0; d < 4; d++) {
                int ny = dy[d] + now.y;
                int nx = dx[d] + now.x;

                if(isValid(ny, nx) && map[ny][nx] != '*') {
                    if(now.dir==d) {
                        if( visited[ny][nx] >= now.turnCount) {
                            Phase next = new Phase(ny, nx, d, now.turnCount);
                            visited[ny][nx] = next.turnCount;
                            pq.add(next);
                        }
                    }
                    else {
                        if( visited[ny][nx] > now.turnCount+1) {
                            Phase next = new Phase(ny, nx, d, now.turnCount + 1);
                            visited[ny][nx] = next.turnCount;
                            pq.add(next);
                        }
                    }
                }
            }

//            for(int i= 0;i<H;i++) {
//                for(int j= 0;j<W;j++) {
//                    System.out.printf("%06d ",visited[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

    }
}
