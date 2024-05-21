
import java.io.*;
import java.util.*;

public class Main {
    static class Food {
        int a,b;

        public Food(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Food{");
            sb.append("a=").append(a);
            sb.append(", b=").append(b);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        List<Food> list = new ArrayList<>(
               );

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Food(a, b));
        }

        // B 값 기준으로 정렬
        list.sort(Comparator.comparingInt(o -> o.b));

        long[] rbest = new long[N + 1];
        Arrays.fill(rbest, Long.MAX_VALUE);

        for (int i = N - 1; i >= 0; i--) {
            rbest[i] = Math.min(rbest[i + 1], list.get(i).a);
        }

        long bestDisc = Long.MAX_VALUE;
        long base = 0;

        for (int k = 1; k <= N; k++) {
            long ans = base + rbest[k - 1];

            base += list.get(k - 1).b;
            bestDisc = Math.min(bestDisc, list.get(k - 1).a - list.get(k - 1).b);
            ans = Math.min(ans, base + bestDisc);
            bw.write(ans + "\n");
        }



        bw.flush();bw.close();



    }
}
