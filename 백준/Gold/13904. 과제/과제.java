import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    static class Assignment implements Comparable<Assignment>{
        int d,w;

        public Assignment(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Assignment o) {
            if(this.w==o.w)
                return o.d - this.d;
            return o.w - this.w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        List<Assignment> list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int d = Integer.parseInt(input[0]);
            int w = Integer.parseInt(input[1]);
            list.add(new Assignment(d, w));
            if(max<d)
                max = d;

        }
        int sum = 0;
        AtomicInteger day =new AtomicInteger(max);
        List<Assignment> temp;
        while (!list.isEmpty()&&day.get()>0) {
            temp = list.stream().filter(assignment -> assignment.d >= day.get()).sorted().collect(Collectors.toList());

            if(!temp.isEmpty()&&temp.get(0).d>=day.get()) {
                Assignment assignment = temp.remove(0);
                list.remove(assignment);
                sum+=assignment.w;
            }
            day.decrementAndGet();
        }
        System.out.println(sum);

    }
}


//6 6 5 day --
//5 day --;
//4 4 60 day --
//3 4 40 day --;
//2 2 50 day--;

