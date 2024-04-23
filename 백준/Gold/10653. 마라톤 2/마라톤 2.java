
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        static int distance(Point p1, Point p2) {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
        }
    }

    static int jump(int current, int rem) {
        if (current == N-1)
            return 0;

        if (dp[current][rem] != -1)
            return dp[current][rem];
        dp[current][rem] = Integer.MAX_VALUE;

        for (int i = 0; i <= rem; i++) {
            if (current + 1 + i >= N)
                break;
            dp[current][rem] = Math.min(dp[current][rem], jump(current + 1 + i, rem - i) + Point.distance(points[current], points[current + 1 + i]));
        }
        return dp[current][rem];
    }

    static int[][] dp;
    static int N,K;
    static Point[] points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());
         points = new Point[N];
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(y, x);
        }
        dp = new int[501][501];
        for(int i= 0;i<501;i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(jump(0,K));
        
    }

}
