
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Fire implements Comparable<Fire> {
        int a,b;

        public Fire(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Fire o) {
            if (this.a == 0 && o.a == 0) {
                return Integer.compare(this.b, o.b);
            } else if (this.a == 0) {
                return Integer.MAX_VALUE;
            } else if (o.a == 0) {
                return Integer.MIN_VALUE+1;
            }

            int a = this.b * o.a;
            int b = o.b * this.a;
            return Integer.compare(a, b);
        }
    }

    private static final int MOD = 40_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Fire> fires = new ArrayList<>();
        for(int i= 0;i<N;i++) {
            var st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            fires.add(new Fire(a, b));
        }

        Collections.sort(fires);

        long time = 0;
        for (Fire fire : fires) {
//            System.out.println("fire.a+\" \"+fire.b = " + fire.a + " " + fire.b);
            long cost = ((time * fire.a % MOD) % MOD + fire.b % MOD) % MOD;
//            long cost = ((time * fire.a) % MOD + fire.b % MOD) % MOD;
//            System.out.println("cost = " + cost);
            time = (time + cost) % MOD;
//            System.out.println("sum = " + sum);
        }
        System.out.println(time);

    }
}
