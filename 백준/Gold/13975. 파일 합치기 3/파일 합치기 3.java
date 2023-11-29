
import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < K; i++) {
                pq.add(Long.parseLong(st.nextToken()));

            }
            long result = 0;
            while (pq.size() >=2) {
                long a = pq.poll();
                long b = pq.poll();
                result+=(a+b);
                pq.add(a + b);

            }
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
