import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Job implements Comparable<Job> {

        int t, s;

        public Job(int t, int s) {
            this.t = t;
            this.s = s;
        }

        @Override
        public int compareTo(Job o) {
            if(o.s==this.s)
                return o.t - this.t;
            return o.s - this.s;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Job> pq = new PriorityQueue<>();

        for(int i= 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Job(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int nowEnd = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            final Job job = pq.poll();
            
            if (nowEnd > job.s) {
                nowEnd = job.s-job.t;

            }
            else {
                nowEnd -= job.t;
            }
            if(nowEnd<0) {
                System.out.println(-1);
                return ;
            }

        }
        System.out.println(nowEnd);
        
    }
}

