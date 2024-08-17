
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[][] A;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    A = new int[N][N];
    int total = 0;
    for (int i = 0; i < N; i++) {
      var st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
        total += A[i][j];
      }
    }
    int min = Integer.MAX_VALUE;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        for (int d1 = 1; d1 < N; d1++) {
          for (int d2 = 1; d2 < N; d2++) {
            visited = new boolean[N][N];
            if(!isPossibleHeight(y, d1, d2)) break;
            if (!isPossibleWidth(y, x, d1, d2)) break;
            visited[y][x] = true;
            dfs1(y,x,1,d1);
            dfs2(y,x,1,d2);
            dfs3(y,x,1,d1, d2);
            dfs4(y,x,1,d1, d2);
//            for(int i= 0;i<N;i++) {
//              for(int j= 0;j<N;j++) {
//                System.out.print((visited[i][j] ? 1 : 0) + " ");
//              }
//              System.out.println();
//            }
//            System.out.println();

            int[] district = new int[5];
            district[0] = leftToRight(0,
                0,
                y + d1,
                x + 1);
            district[2] = leftToRight(y + d1,
                0,
                N,
                x - d1 + d2);
            district[1] = rightToLeft(
                0,
                x + 1,
                y + d2 + 1,
                N - 1);
            district[3] = rightToLeft(y + d2 + 1,
                x - d1 + d2,
                N,
                N - 1);
            district[4] = total-district[0]-district[1]-district[2]-district[3];

            Arrays.sort(district);
            if(district[0]!=0) {
              min = Math.min(min, district[4] - district[0]);
            }
          }
        }
      }
    }
    System.out.println(min);

  }

  private static int leftToRight(int startY, int startX, int endY, int endX) {
    int temp = 0;
    for (int i = startY; i < endY; i++) {

      for (int j = startX; j < endX; j++) {
        if(visited[i][j]) break;

        temp += A[i][j];
      }
    }
    return temp;
  }

  private static int rightToLeft(int startY, int startX, int endY, int endX) {
    int temp = 0;
    for (int i = startY; i < endY; i++) {

      for (int j = endX; j >= startX; j--) {
        if(visited[i][j]) break;
        temp += A[i][j];
      }
    }
    return temp;
  }

  static void dfs1(int y, int x, int count, int d1) {
    if(count > d1)
      return;
    visited[y+count][x-count] = true;
    dfs1(y, x, count + 1, d1);
  }
  static void dfs2(int y, int x, int count, int d2) {
    if(count > d2)
      return;
    visited[y+count][x+count] = true;
    dfs2(y, x, count + 1, d2);
  }

  static void dfs3(int y, int x, int count, int d1, int d2) {
    if(count > d2) {
      return;
    }
    visited[y+d1 + count][x - d1 + count] = true;
    dfs3(y,x,count+1, d1, d2);
  }

  static void dfs4(int y, int x, int count, int d1, int d2) {
    if(count > d1) {
      return;
    }
    visited[y+d2+ count][x + d2 - count] = true;
    dfs4(y,x,count+1, d1, d2);
  }
  static boolean[][] visited;
  private static boolean isPossibleHeight(int y, int d1, int d2) {
    return y + d1 + d2 < N;
  }

  private static boolean isPossibleWidth(int y, int x, int d1, int d2) {
    return x - d1 >= 0 && x + d2 < N;
  }
}
