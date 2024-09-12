
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

            boolean[][] visited = new boolean[N + 1][N + 1];

            ArrayDeque<Point> q = new ArrayDeque<>();
            for (int score : scores) {
                if(score <=N) {
                    q.add(new Point(score, score));
                    visited[score][score] = true;
                }
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
                    if (nextScore <= N  && count <= N && !visited[count][nextScore]) {
                        visited[count][nextScore] = true;
                        q.add(new Point(nextScore, count));
                    }
                }

            }
            bw.write(max + "\n");

        }
        bw.flush();bw.close();
    }

}
