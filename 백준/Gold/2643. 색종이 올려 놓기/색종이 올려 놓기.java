import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Confetti implements Comparable<Confetti> {
        int[] width;

        public Confetti(int[] width) {
            if(width[0]<width[1]) {
                int temp = width[0];
                width[0] = width[1];
                width[1] = temp;
            }
            this.width = width;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Confetti{");
            sb.append("width=");
            if (width == null) sb.append("null");
            else {
                sb.append('[');
                for (int i = 0; i < width.length; ++i)
                    sb.append(i == 0 ? "" : ", ").append(width[i]);
                sb.append(']');
            }
            sb.append('}');
            return sb.toString();
        }

        @Override
        public int compareTo(Confetti o) {
            if (this.width[0] == o.width[0]) {
                return Integer.compare(this.width[1], o.width[1]);
            }
            return Integer.compare(this.width[0], o.width[0]);
        }


    }
    static Confetti[] confettis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        confettis = new Confetti[N];
        for(int i= 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            confettis[i] = new Confetti(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});

        }

        Arrays.sort(confettis);

        int[] dp = new int[N];

        dp[0] = 1;
        for(int i = 1;i<N;i++) {
            dp[i] = 1;
            for(int j = 0;j<i;j++) {
                if (compare(confettis[j], confettis[i])) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());

    }
    static boolean compare(Confetti c1, Confetti c2) {
        // c1 <= c2 => true

        if(c1.width[0] <= c2.width[0]) {
            if (c1.width[1] <= c2.width[1]) {
                return true;
            }
        }
        return false;

    }

}
