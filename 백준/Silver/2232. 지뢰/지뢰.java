import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static class Mine {
        int num, p;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Mine{");
            sb.append("num=").append(num);
            sb.append(", p=").append(p);
            sb.append('}');
            return sb.toString();
        }

        public Mine(int num, int p) {
            this.num = num;
            this.p = p;
        }
    }
   static int[] ary;
    static void findLeft (int index) {
        if(index>0 && ary[index]>ary[index-1]) {
            findLeft(index-1);

        }
        ary[index] = Integer.MIN_VALUE;

    }
    static void findRight (int index) {

        if(index<N && ary[index]>ary[index+1]) {
            findRight(index+1);
        }
        ary[index] = Integer.MIN_VALUE;
    }
    static boolean find(int now, int index) {
        
        if(ary[index]==Integer.MIN_VALUE) {
            return false;
        }
        findLeft(index);
        ary[index] = now;
        findRight(index);

        return true;
    }
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList<Integer> result = new ArrayList<>();
        ary = new int[N+1];

        PriorityQueue<Mine> pq = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        for(int i= 1;i<=N;i++) {
            ary[i] = Integer.parseInt(br.readLine());
            pq.add(new Mine(i, ary[i]));
        }
        while (!pq.isEmpty()) {
            final Mine mine = pq.poll();
//            System.out.println("mine = " + mine);

            if(find(mine.p, mine.num)) {
                result.add(mine.num);
            }
//            for (int i : ary) {
//                System.out.print(i+" ");
//            }
//            System.out.println();
        }


        result.stream().sorted().forEach(System.out::println);

    }
}
