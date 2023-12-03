import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static class Office implements Comparable<Office> {

        long locate, number;

        public Office(long l, long n) {
            this.locate = l;
            this.number = n;
        }

        @Override
        public int compareTo(Office o) {
            return Long.compare(this.locate, o.locate);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Office[] ary = new Office[N];
        long[] sums = new long[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int a = Integer.parseInt(input[1]);
            ary[i] = new Office(x, a);

        }
        Arrays.sort(ary);
        sums[0] = ary[0].number;
        for (int i = 1; i < N; i++) {
            sums[i] = ary[i].number + sums[i - 1];
        }

        long max = sums[N - 1] / 2;
        if (sums[N - 1] % 2 != 0)
            max += 1;
        for (int i = 0; i < N ; i++) {
            if (max <= sums[i]) {
                System.out.println(ary[i].locate);
                break;
            }
        }
    }
}