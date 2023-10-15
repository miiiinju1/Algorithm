
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i= 0;i<N;i++)
        {
            long temp = Long.parseLong(br.readLine());
            pq.add(temp);
        }

        long sum = 0;
        while (!pq.isEmpty()) {

            if (pq.size() == 1) {
                System.out.println(sum);
                return ;
            }

            long A = pq.poll();
            long B = pq.poll();
            sum += (A + B);
            pq.add(A + B);
        }
    }
}
