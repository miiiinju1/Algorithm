
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
        int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[][] map = new int[M][N];

        for(int i=0;i<M;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        int count =0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 1) {
                    count++;

                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i, j));

                    while (!q.isEmpty()) {

                        Point now = q.poll();
                        if(map[now.y][now.x]==0)
                            continue;
                        map[now.y][now.x]=0;
                        for (int d = 0; d < 8; d++) {
                            int y = now.y+dy[d];
                            int x = now.x+dx[d];
                            if (y >= 0 && x >= 0 && y < M && x < N) {
                                q.add(new Point(y, x));
                            }

                        }

                    }
                }
            }
        }
        System.out.println(count);
    }
}
