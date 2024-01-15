import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Carrot implements Comparable<Carrot>{
        int w,p;

        public Carrot(int w, int p) {
            this.w = w;
            this.p = p;
        }

        @Override
        public int compareTo(Carrot o) {
            if (this.p == o.p) {
                return Integer.compare(o.w, this.w);
            }
            return Integer.compare(o.p, this.p);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        long sum = 0;
        PriorityQueue<Carrot> pq = new PriorityQueue<>();
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Carrot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }


        int i = T-1;

        while(!pq.isEmpty()) {
            final Carrot now = pq.poll();

            sum+= now.w+(long)i--*now.p;
        }
        
        System.out.println(sum);
    }
}
