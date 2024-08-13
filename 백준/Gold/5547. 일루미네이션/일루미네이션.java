
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

  static int[][] map, check;
  static int extendH, extendW;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int W = Integer.parseInt(st.nextToken());
    int H = Integer.parseInt(st.nextToken());

    extendW = 4 * W + 3;
     extendH = 6 * H + 8;
    map = new int[extendH+2][extendW+2];

    for(int i = 0;i<H;i++) {
      st = new StringTokenizer(br.readLine());

      if(i%2==0) {
        int y = 8 * (i / 2) +1;
        for(int j =0;j<W;j++) {
          int input = Integer.parseInt(st.nextToken());
          if(input==0) continue;
          int x = (j * 4) + 4 +1;

          map[y][x] = 1;
          for(int z = x-1;z<=x+1;z++) {
            map[y+1][z]=1;
          }
//          map[y+1][x-1] = 1;
//          map[y+1][x+1] =1;
          for(int z = x-2;z<=x+2;z++) {
            map[y+2][z]=1;
          }
//          map[y + 2][x - 2] = 1;
//          map[y + 2][x + 2] = 1;
          for(int z = x-2;z<=x+2;z++) {
            map[y+3][z]=1;
          }
//          map[y + 3][x - 2] = 1;
//          map[y + 3][x + 2] = 1;
          for(int z = x-2;z<=x+2;z++) {
            map[y+4][z]=1;
          }
          for(int z = x-1;z<=x+1;z++) {
            map[y+5][z]=1;
          }
//          map[y + 4][x - 1] = 1;
//          map[y + 4][x + 1] = 1;
          map[y + 6][x] = 1;
        }
      }
      else {
        int y = 8 * (i/2) + 4+1;
        for(int j = 0;j<W;j++) {
          int input = Integer.parseInt(st.nextToken());
          if(input==0) continue;
          int x = (j * 4) + 2+1;
          map[y][x] = 1;
          for(int z = x-1;z<=x+1;z++) {
            map[y+1][z]=1;
          }
//          map[y+1][x-1] = 1;
//          map[y+1][x+1] =1;
          for(int z = x-2;z<=x+2;z++) {
            map[y+2][z]=1;
          }
//          map[y + 2][x - 2] = 1;
//          map[y + 2][x + 2] = 1;
          for(int z = x-2;z<=x+2;z++) {
            map[y+3][z]=1;
          }
//          map[y + 3][x - 2] = 1;
//          map[y + 3][x + 2] = 1;
          for(int z = x-2;z<=x+2;z++) {
            map[y+4][z]=1;
          }
          for(int z = x-1;z<=x+1;z++) {
            map[y+5][z]=1;
          }
//          map[y + 4][x - 1] = 1;
//          map[y + 4][x + 1] = 1;
          map[y + 6][x] = 1;
        }

      }


    }

    check = new int[extendH+2][extendW+2];

    visited = new boolean[extendH+2][extendW+2];
    Deque<Point> q = new ArrayDeque<>();

    visited[0][0] = true;
    q.add(new Point(0, 0));

    int m = 0;
    while (!q.isEmpty()) {
      final Point now = q.poll();

      if (map[now.y][now.x] == 1) {
        check[now.y][now.x] = 1;
        m++;
        continue;
      }

      for (int d = 0; d < 4; d++) {
        int y = dy[d] + now.y;
        int x = dx[d] + now.x;

        if (y >= 0 && x >= 0 && y <= extendH + 1 && x <= extendW + 1 && !visited[y][x]
            && map[y][x] != -2) {
          visited[y][x] = true;
          q.add(new Point(y, x));
        }

      }
    }
    System.out.println(m/2);
//    visited = new boolean[extendH+2][extendW+2];
//
//    int result = 0;
//    for(int i =0;i<extendH+2;i++) {
//      for(int j= 0;j<extendW+2;j++) {
//        if(check[i][j]==1 && !visited[i][j]) {
////          visited[i][j] = true;
//          count = -1;
//          dfs(i, j, 0, -1, -1);
//          result += count/2;
//        }
//      }
//    }
//
//    System.out.println(result);

  }
  static boolean[][] visited;
  static int count = 0;

  static void dfs(int y, int x, int c, int befY, int befX) {
//    System.out.println("y + \" \" + x = " + y + " " + x);
    count++;
    
    for(int d = 0;d<8;d++) {
      int Y = dy[d] + y;
      int X = dx[d] + x;

      if (Y >= 0 && X >= 0 && Y <= extendH + 1 && X <= extendW + 1
          && check[Y][X]==1&& (befY!=Y || befX!=X) && !visited[Y][X]) {
        
        visited[Y][X] = true;
        dfs(Y, X, c + 1, y, x);

      }
    }
  }

  static int[] dy = {-1, 0, 1, 0, -1, -1, 1, 1};
  static int[] dx = {0, -1, 0, 1, 1, -1, 1, -1};

  static class Point {
    int y, x;

    public Point(int y, int x) {
      this.y = y;
      this.x = x;
    }
  }

}
//8 4
//0 1 0 0 0 0 0 0
//0 1 1 0 0 0 0 0
//1 0 1 0 0 0 0 0
//0 1 1 0 0 0 0 0