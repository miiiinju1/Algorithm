import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for(int i= 0;i<N;i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0;
        for(int i= 1;i<list.size();i++) {
            int a = list.get(i-1);
            int b = list.get(i);
            int diff = b-a;
            sum+=diff;
            pq.add(diff);
        }

        for(int i = 1;i<K;i++) {
            if(!pq.isEmpty())
            sum -= pq.poll();
        }

        System.out.println(sum);




    }
}
