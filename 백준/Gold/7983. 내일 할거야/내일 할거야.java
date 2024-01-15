import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Assignment implements Comparable<Assignment> {
        int d,t;

        public Assignment(int d, int t) {
            this.d = d;
            this.t = t;
        }

        @Override
        public int compareTo(Assignment o) {
            if(o.t==this.t) {
                return Integer.compare(o.d, this.d);
            }
            return Integer.compare(o.t, this.t);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Assignment> pq = new PriorityQueue<>();
        for(int i= 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Assignment(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

        }

        int max = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            final Assignment now = pq.poll();

            if(now.t<max) {
                max = now.t;
            }

            max-= now.d;

        }
        System.out.println(max);



    }
}
