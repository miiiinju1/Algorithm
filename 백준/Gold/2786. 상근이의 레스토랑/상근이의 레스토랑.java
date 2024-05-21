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

        List<Food> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Food(a, b));
        }

        list.sort(Comparator.comparingInt(o -> o.b));

        long base = 0;
        int diff = Integer.MAX_VALUE;

        int[] afterMinA = new int[N];

        afterMinA[N - 1] = list.get(N - 1).a;
        for (int i = N - 2; i >= 0; i--) {
            afterMinA[i] = Math.min(afterMinA[i + 1], list.get(i).a);
        }


        //pq로는 안 됨 -> 마지막 그걸 알고 있어야함


        for (int i = 0; i < N; i++) {

            Food food = list.get(i);
            diff = Math.min(diff, food.a - food.b);

            bw.write((base + Math.min(food.b+diff, afterMinA[i]) + "\n"));
            base += food.b;

        }
        bw.flush();
        bw.close();
    }

}
