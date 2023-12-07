import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int x,time;

        public Point(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if(N==K) {
            System.out.println(0);
            return ;
        }
        int answer = K+1;
        int moveSpeed = 1;
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(N, 1));
        boolean[][] visited = new boolean[2][500001];
        int i = 1;
        while (!q.isEmpty()) {
            Point point = q.poll();
            int now = point.x;
            if(point.time>i) {
                moveSpeed += ++i;
                answer= K + moveSpeed;
                if(answer>500000)
                    break;
                if (visited[point.time % 2][answer]) {
                    System.out.println(point.time);
                    return;
                }

            }
            int next = now + 1;
            if (next <= 500000) {
                if (answer==next) {
                    System.out.println(point.time);
                    return;
                }
                else {
                    if(!visited[point.time%2][next]) {
                        q.add(new Point(next, point.time + 1));
                        visited[(point.time)%2][next] = true;
                    }

                }
            }
            next = now - 1;
            if (next >= 0) {
                if (answer==next) {
                    System.out.println(point.time);
                    return;
                } else {
                    if(!visited[point.time%2][next]) {


                        q.add(new Point(next, point.time + 1));
                        visited[(point.time)%2][next] = true;


                    }

                }
            }
            next = now * 2;
            if (next <= 500000) {
                if (answer==next) {
                    System.out.println(point.time );
                    return;
                } else {
                    if(!visited[point.time%2][next]) {

                        q.add(new Point(next, point.time + 1));
                        visited[(point.time)%2][next] = true;

                    }

                }

            }
        }

        System.out.println(-1);

    }
}
