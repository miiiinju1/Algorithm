import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Flower implements Comparable<Flower> {
        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(Flower o) {
            if(this.start==o.start) {
                return o.end - this.end;
            }
            return this.start-o.start;

        }
    }
    static int[]  month = {0,31,28,31,30,31,30,31,31,30,31,30,31};

      public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
        ;
            for(int i=2;i<13;i++) {
                month[i] += month[i -1];
            }
            PriorityQueue<Flower> pq = new PriorityQueue<>();


            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = month[Integer.parseInt(st.nextToken())-1] + Integer.parseInt(st.nextToken());
                int end = month[Integer.parseInt(st.nextToken())-1] + Integer.parseInt(st.nextToken());
                pq.add(new Flower(start, end));
            }

            int endDate = month[11];

            pq.add(new Flower(endDate+1, month[12]));


            int nowStart = 0;
            int nowEnd = 60;

            int count =0;
            Flower prev = pq.peek();
            if(prev.start>60)
            {
                System.out.println(0);
                return ;
            }
            while (!pq.isEmpty()) {
                Flower flower = pq.poll();
                if(flower.start>=nowStart&&flower.start<=nowEnd&&flower.end>nowEnd) {

                    if(prev.end<flower.end) {
                        prev = flower;

                    }
                }
                else if(flower.start>nowEnd&&prev.start>=nowStart&&prev.start<=nowEnd) {
                    nowStart = prev.start+1;
                    nowEnd = prev.end;
                    count++;
                    prev = flower;
                    if(nowEnd>endDate)
                        break;


                }


            }
            if(nowEnd>endDate) {
                System.out.println(count);
                return ;
            }
        System.out.println(0);
    }
}