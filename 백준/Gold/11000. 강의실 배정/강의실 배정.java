import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Lec implements Comparable<Lec>{
        int start, end;

        public Lec(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lec o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start-o.start;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        PriorityQueue<Lec> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Lec(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        PriorityQueue<Integer> map = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Lec lec = pq.poll();

            if (!map.isEmpty()) {
                int before = map.peek();
                if(before<=lec.start) {
                    map.poll();
                    map.add(lec.end);
                }
                else{
                    map.add(lec.end);
                }
            }
            else {
                map.add(lec.end);
            }
        }
        System.out.println(map.size());
    }
}
