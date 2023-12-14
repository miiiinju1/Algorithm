import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static class Point {
        int y,x;
        int type;

        public Point(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }

    static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[8][8];
        ArrayList<Point> set = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j= 0;j<8;j++) {
                if (map[i][j] == '#') {
                    set.add(new Point(i, j, -1));
                }
            }
        }

        ArrayDeque<Point> q = new ArrayDeque<>();

        Collections.reverse(set);
        q.add(new Point(7, 0, 0));
        q.addAll(set);


        while(!q.isEmpty()) {
            Point now = q.poll();
            if(now.type==-1) {
                if (now.y < 7) {
                    map[now.y][now.x] ='.';
                    map[now.y+1][now.x] ='#';
                    q.add(new Point(now.y + 1, now.x, now.type));
                }
                else {
                    map[now.y][now.x] = '.';
                }
            }
            else {


                if(map[now.y][now.x]=='#')
                    continue;
                for(int i= 0;i<9;i++) {
                    int y = dy[i] + now.y;
                    int x = dx[i] + now.x;

                    if (y >= 0 && x >= 0 && y < 8 && x < 8&&map[y][x]=='.') {
                        if(y==0&&x==7) {
                            System.out.println(1);
                            return ;
                        }
                        q.add(new Point(y, x, now.type));
                    }
                }
            }
        }

        System.out.println(0);
    }
}
