
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Lec implements Comparable<Lec>{
        int p, d;

        public Lec(int p, int d) {
            this.p = p;
            this.d = d;
        }


        @Override
        public int compareTo(Lec o) {
            if(this.p==o.p)
                return o.d - this.d;
            return o.p-this.p;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[10001];
        PriorityQueue<Lec> pq = new PriorityQueue<>();
        for(int i= 0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());


            pq.add(new Lec(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));

        }

        long sum = 0;
        while(!pq.isEmpty()) {
            Lec lec = pq.poll();
            for(int i = lec.d;i>0;i--) {
                if(!visited[i]) {
                    visited[i] = true;
                    sum += lec.p;
                    break;
                }

            }


        }  System.out.println(sum);


    }
}
