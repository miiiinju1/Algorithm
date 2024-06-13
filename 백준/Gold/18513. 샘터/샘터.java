
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int x, depth;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("x=").append(x);
            sb.append(", depth=").append(depth);
            sb.append('}');
            return sb.toString();
        }

        public Point(int x, int depth) {
            this.x = x;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] ary = new int[N];
        Set<Integer> visited = new HashSet<>();
        Deque<Point> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
            visited.add(ary[i]);
            q.add(new Point(ary[i], 1));

        }

        long sum = 0;


        while (!q.isEmpty() && K > 0) {
            final Point now = q.poll();

//            System.out.println("now = " + now);

            int next = now.x + now.depth;

            if (!visited.contains(next)) {
                visited.add(next);
                --K;
                sum += now.depth;
                q.add(new Point(now.x, now.depth + 1));
            }

            if(K<=0)
                break;

            next = now.x - now.depth;
            if (!visited.contains(next)) {
                visited.add(next);
                --K;
                sum += now.depth;
                q.add(new Point(now.x, now.depth + 1));
            }
        }


//        int distance = 1;
//        while (K > 0) {
//            int temp = 0;
//            for (int i = 0; i < N; i++) {
//                int next = ary[i] + distance;
//
//                if (!visited.contains(next)) {
//                    visited.add(next);
//                    --K;
//                    ++temp;
//                }
//                if (K <= 0) {
//                    break;
//                }
//
//                next = ary[i] - distance;
//                if (!visited.contains(next)) {
//                    visited.add(next);
//                    --K;
//                    ++temp;
//                }
//                if (K <= 0) {
//                    break;
//                }
//            }
////            System.out.println(temp+" "+distance);
//            sum += ((long)distance * temp);
//            ++distance;
//
//        }

        System.out.println(sum);
    }
}
//2 6
//0 4