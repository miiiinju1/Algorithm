import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Duck {
        int location, depth;

        boolean type;

        public Duck(int location, boolean type, int depth) {
            this.location = location;
            this.type = type;
            this.depth = depth;
        }
    }
    static final boolean FIVE = false;
    static final boolean SIX = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] map = new int[N+1];

        Arrays.fill(map, -1);
        ArrayDeque<Duck> q = new ArrayDeque<>();
        map[A] = 0 ;

        q.add(new Duck(A, FIVE, 1));
        q.add(new Duck(B, SIX, 1));

        while (!q.isEmpty()) {
            Duck now = q.poll();
            int d = (int) Math.pow(2, now.depth-1);

            int x = now.location + d;

            if(now.type==FIVE) {
                if (x > 0 && x <= N) {
                    map[x] = now.depth;
                    q.add(new Duck(x, FIVE, now.depth + 1));
                }
                x = now.location - d;
                if (x > 0 && x <= N) {
                    map[x] = now.depth;
                    q.add(new Duck(x, FIVE, now.depth + 1));
                }
            }
            else {
                if (x > 0 && x <= N) {
                    if(map[x]==now.depth) {
                        System.out.println(now.depth);
                        return;
                    }else {
                        q.add(new Duck(x, SIX, now.depth + 1));
                    }
                }
                x = now.location - d;
                if (x > 0 && x <= N) {
                    if(map[x]==now.depth) {
                        System.out.println(now.depth);
                        return;
                    }else {
                        q.add(new Duck(x, SIX, now.depth + 1));
                    }
                }


            }
        }


        System.out.println(-1);

    }
}
