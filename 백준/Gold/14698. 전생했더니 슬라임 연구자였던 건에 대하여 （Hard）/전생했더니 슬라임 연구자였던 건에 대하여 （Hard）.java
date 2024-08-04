
import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long sum = 1L;
            PriorityQueue<Long> pq = new PriorityQueue<>();

            for(int i= 0;i<n;i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }


            while (pq.size() > 1) {
                Long val1 = pq.poll();
                Long val2 = pq.poll();

                long mul = val1 * val2;
                pq.add(mul);
                sum = ((sum%MOD ) * (mul%MOD ))%MOD;
            }

            bw.write(sum + "\n");


        }
        bw.flush();bw.close();
    }
}
