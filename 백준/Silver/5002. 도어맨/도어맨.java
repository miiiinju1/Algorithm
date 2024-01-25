import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static class Person implements Comparable<Person>{
        int value, index;

        public Person(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.index,o.index);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());

        final String str = br.readLine();

        PriorityQueue<Person> pq = new PriorityQueue<>();

        for(int i = 0;i<str.length();i++) {
            int value;
            if(str.charAt(i)=='W') {
                value=-1;
            }
            else {
                value=1;
            }
            pq.add(new Person(value, i));
        }

        int now = 0;
        int count = 0;
        while(!pq.isEmpty()) {
            final Person poll = pq.poll();

            //터지면
            if(Math.abs(now+poll.value)>X) {
                if(pq.isEmpty()) {
                    break;
                }
                else {
                    final Person poll2 = pq.poll();

                    if(Math.abs(now+poll2.value)>X) {
                        break;
                    }
                    else {
                        now+=poll2.value;
                        pq.add(poll);
                        count++;
                    }

                }
            }
            else {
                count++;
                now+=poll.value;

            }
        }
        System.out.println(count);

    }
}

//WWWMM