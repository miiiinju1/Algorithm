
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int score, count;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("score=").append(score);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }

        public Point(int score, int count) {
            this.score = score;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            var st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] scores = new int[M];
            st = new StringTokenizer(br.readLine());
            for(int i= 0;i<M;i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }

            int gradeMax = N/3 *2;
            boolean[][] visited = new boolean[N + 1][gradeMax];

            ArrayDeque<Point> q = new ArrayDeque<>();
            for (int score : scores) {
                q.add(new Point(score, score));
            }

            int max = -1;

            while(!q.isEmpty()) {
                Point point = q.poll();
                if(point.count==N) {
                    max = Math.max(max, point.score);
                    continue;
                }

                for (int score : scores) {
                    int nextScore = point.score + score;
                    int count = point.count + nextScore;
                    if (nextScore < gradeMax  && count < N + 1 && !visited[count][nextScore]) {
                        visited[count][nextScore] = true;
                        q.add(new Point(nextScore, count));
                    }
                }

            }
//
//            int[][] dp = new int[N / 2][N + 1];
//            for(int i= 0;i<N/2;i++) {
//                Arrays.fill(dp[i], -1);
//
//            }
//
//            for (int score : scores) {
//                dp[0][score] = score;
//            }
//
//            for (int i = 0; i < N / 2 - 1; i++) {
//                for (int j = 1; j <= N; j++) {
//                    if (dp[i][j] != -1) {
//                        for (int score : scores) {
//                            int nextScore = dp[i][j]+score;
//                            int nextCount = j + nextScore;
//                            // 팔굽혀펴기 횟수
//                            if (nextCount <= N) {
//                                // 값을 최대로 변경
//                                dp[i + 1][nextCount] = Math.max(dp[i + 1][nextCount], nextScore);
//                            }
//                        }
//                    }
//                }
//            }
//            System.out.println();
//            for(int i= 0;i<N/2;i++) {
//                for(int j= 0;j<=N;j++) {
//                    System.out.printf("%2d ",dp[i][j]);
//                }
//                System.out.println();
//
//            }

//            int max = -1;
//            for(int i= 0;i<N/2;i++) {
//                max = Math.max(max, dp[i][N]);
//            }

            bw.write(max + "\n");

        }
        bw.flush();bw.close();
    }

}
