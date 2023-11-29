
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static class Lec implements Comparable<Lec> {

        int start, end;

        public Lec(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lec o) {
            if(this.start==o.start)
                return this.end-o.end;
            return this.start-o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Lec> pq = new PriorityQueue<>();
        for(int i= 0;i<N;i++) {
            String[] str = br.readLine().split(" ");
            int start = Integer.parseInt(str[1]);
            int end = Integer.parseInt(str[2]);
            pq.add(new Lec(start, end));
           
        }
        PriorityQueue<Integer> result = new PriorityQueue<>();
        result.add(pq.poll().end);
        while (!pq.isEmpty()) {
            Lec lec = pq.poll();

            if(lec.start>=result.peek()) {
                result.poll();
            }
            result.add(lec.end);

        }
        System.out.println(result.size());
    }
}

