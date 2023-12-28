import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static class Mine {
        int num, p;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
            if(find(mine.p, mine.num)) {
                result.add(mine.num);
            }
//
        }


        result.stream().sorted().forEach(c -> {
            try {
                bw.write(c+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bw.flush();
bw.close();

    }
}
