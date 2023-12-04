import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Interval implements Comparable<Interval>{
        int distance,a,b;

        public Interval(int a, int b) {
            this.distance = b-a;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Interval o) {
            if(o.distance==this.distance) {
                return this.a - o.a;
            }
            return o.distance- this.distance;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] ary = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        PriorityQueue<Interval> pq = new PriorityQueue<>();

        for(int i= 1;i<N;i++) {
            pq.add(new Interval(ary[i - 1], ary[i]));
        }
        for(int i= 1;i<K;i++) {
            pq.poll();
        }
        long sum = 0L;
        while(!pq.isEmpty()) {
            sum += pq.poll().distance;
        }
        System.out.println(sum);


    }
}

