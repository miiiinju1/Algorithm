import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

  static int[][] map;
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
          for(int z = x-2;z<=x+2;z++) {
            map[y+2][z]=1;
          }
          for(int z = x-2;z<=x+2;z++) {
            map[y+3][z]=1;
          }
          for(int z = x-2;z<=x+2;z++) {
            map[y+4][z]=1;
          }
          for(int z = x-1;z<=x+1;z++) {
            map[y+5][z]=1;
          }
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
          for(int z = x-2;z<=x+2;z++) {
            map[y+2][z]=1;
          }
          for(int z = x-2;z<=x+2;z++) {
            map[y+3][z]=1;
          }
          for(int z = x-2;z<=x+2;z++) {
            map[y+4][z]=1;
          }
          for(int z = x-1;z<=x+1;z++) {
            map[y+5][z]=1;
          }
          map[y + 6][x] = 1;
        }

      }


    }
    visited = new boolean[extendH+2][extendW+2];
    Deque<Point> q = new ArrayDeque<>();

    visited[0][0] = true;
    q.add(new Point(0, 0));

    int m = 0;
    while (!q.isEmpty()) {
      final Point now = q.poll();

      if (map[now.y][now.x] == 1) {
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


  }
  static boolean[][] visited;

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