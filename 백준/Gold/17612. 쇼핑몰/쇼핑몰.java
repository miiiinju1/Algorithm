
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Casher implements Comparable<Casher> {

        int num, out;


        public Casher(int num, int out) {
            this.num = num;
            this.out = out;
        }

        @Override
        public int compareTo(Casher o) {
            if (this.out == o.out) {
                return Integer.compare(this.num, o.num);
            }
            return Integer.compare(this.out, o.out);
        }
    }

    static PriorityQueue<Casher> cashers = new PriorityQueue<>();
    static class Person {
        int id, num, outTime;

        public Person(int id, int num, int outTime) {
            this.id = id;
            this.num = num;
            this.outTime = outTime;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i = 0;i<k;i++) {
            cashers.add(new Casher(i, 0));
        }

        PriorityQueue<Person> people = new PriorityQueue<>((o1, o2) -> {
            if(o1.outTime == o2.outTime) {
                return Integer.compare(o2.num, o1.num);
            }
            return Integer.compare(o1.outTime, o2.outTime);
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());


            Casher poll = cashers.poll();
            poll.out += w;
            people.add(new Person(id, poll.num, poll.out));
            cashers.add(poll);
        }


        long sum = 0L;
        int m = 1;

        while(!people.isEmpty()) {
            Person poll = people.poll();
            sum += (long) poll.id * m;
            m++;
        }

        System.out.println(sum);

    }

}
