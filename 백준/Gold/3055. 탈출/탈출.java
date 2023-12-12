import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static class Point{


        int y, x;
        char depth;

        public Point(int y, int x, char depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
    }

    static int destY = -1, destX = -1, startY = -1, startX = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        ArrayList<Point> water = new ArrayList<>();
        for(int i= 0;i<R;i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (input[j] == '*') {
                    water.add(new Point(i, j, '*'));
                } else if (input[j] == 'D') {
                    destY = i;
                    destX = j;
                    input[j] = '0';
                } else if (input[j] == 'S') {
                    startY = i;
                    startX = j;
                    input[j] = '0';
                } else if(input[j]=='X') {
                    input[j] = 'X';
                } else {
                    input[j] = '0';
                }
                map[i][j] = input[j];
            }
        }


        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(startY, startX, '0'));
        for (Point point : water) {
            for (int i = 0; i < 4; i++) {
                int y = point.y + dy[i];
                int x = point.x+ dx[i];
                if (y >= 0 && x >= 0 && y < R && x < C && map[y][x] == '0') {
                    q.add(new Point(y, x, '*'));
                    map[y][x] = '*';

                }
            }
        }
        
        while (!q.isEmpty()) {
            Point point = q.poll();

            if(point.depth == '*') {
                for(int i = 0;i<4;i++) {
                    int y = point.y + dy[i];
                    int x = point.x + dx[i];
                    if (!(y == destY && x == destX)) {

                        if (y >= 0 && x >= 0 && y < R && x < C && map[y][x] != '*' && map[y][x] != 'X') {
                            q.add(new Point(y, x, '*'));
                            map[y][x] = '*';
                        }
                    }
                }
            }

            else {
                if (map[point.y][point.x] < point.depth + 1) {
                    map[point.y][point.x] = (char)(point.depth+1);
                }
                else {
                    continue;
                }
                for(int i = 0;i<4;i++) {
                    int y = point.y +dy[i];
                    int x = point.x + dx[i];

                    if(y==destY&&x==destX) {
                        System.out.println(point.depth-47);
                        return ;
                    }
                    if(y>=0&&x>=0&&y<R&&x<C&&map[y][x]!='*'&&map[y][x]<point.depth+1&&map[y][x]!='X') {
                        q.add(new Point(y, x, (char) (point.depth + 1)));
                    }
                }

            }

        }
        System.out.println("KAKTUS");
    }
}

//          ....***XXXD
//          S..........
//