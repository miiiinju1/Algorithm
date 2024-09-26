
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {

        int y, x;

        int minValue, maxValue;

        int diff;
        public Node(int y, int x, int minValue, int maxValue) {
            this.y = y;
            this.x = x;
            this.minValue = minValue;
            this.maxValue = maxValue;
            diff = maxValue - minValue;
        }
    }

    static int n;
    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < n && x < n;
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; ++i) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        int[][][] distance = new int[n][n][201];

        for(int i= 0;i<n;++i) {
            for(int j= 0;j<n;++j) {
                for(int k = 0;k<201;++k) {
//                    for(int l = 0;l<201;++l){
                        distance[i][j][k] = Integer.MAX_VALUE;
//                    }
                }
            }
        }


        distance[0][0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.diff));

        pq.add(new Node(0, 0, map[0][0], map[0][0]));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.y == n - 1 && now.x == n - 1) {
                System.out.println(now.diff);
                return;
            }

            for (int d = 0; d < 4; ++d) {
                int y = dy[d] + now.y;
                int x = dx[d] + now.x;

                if (isValid(y, x)) {
                    int max = Math.max(now.maxValue, map[y][x]);
                    int min = Math.min(now.minValue, map[y][x]);
                    int nextDiff = max - min;

                    if(distance[y][x][max] > nextDiff) {
                        distance[y][x][max] = nextDiff;
                        pq.add(new Node(y, x, min, max));
                    }

                }
            }


        }





    }

}

