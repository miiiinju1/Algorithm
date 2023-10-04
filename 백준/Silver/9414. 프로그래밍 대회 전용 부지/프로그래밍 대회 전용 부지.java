import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static long limit = 5000000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int z=0;z<T;z++)
        {
            long sum=0;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int n=0,count=0;
            while((n=Integer.parseInt(br.readLine()))!=0)
            {
                count++;
                pq.add(n);
            }

            while(!pq.isEmpty()) {
                int now = pq.poll();

                long pow_sum = 1;
                for (int i = 1; i <=count; i++) {
                    pow_sum*=now;
                    if(pow_sum>limit) {
                        break;
                    }
                }
                sum+=(2*pow_sum);
                if(sum>limit)
                    break;
                count--;
            }

            if(sum>limit)
                System.out.println("Too expensive");
            else
                System.out.println(sum);
        }
    }

}
