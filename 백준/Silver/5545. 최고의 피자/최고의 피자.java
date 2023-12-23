import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        double dough = Double.parseDouble(br.readLine());

        double max = dough/A;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int n = 0;n<N;n++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        double before = dough;

        while(!pq.isEmpty()) {
            Integer topping = pq.poll();
            before+=topping;
            A+=B;

            if(before/A<=max) {
                System.out.println((int)max);

                return ;
            }
            else {
                max = before/A;
            }
            
        }

  System.out.println((int)max);
    }
}
