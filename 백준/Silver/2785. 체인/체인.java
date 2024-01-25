
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i= 0;i<N;i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        int count = N-1;
        int result = 0;
        while(!pq.isEmpty()) {
            final Integer poll = pq.poll();
            count--;

            if(poll>count) {
                if(count==0) {
                    result+=1;
                }
                else {
                    result += (count + 1);
                }
                break;
            }
            else if(poll<count) {
                count-=poll;
                result+=poll;
                if(count==1) {
                    break;
                }

            }
            else {
                result+=poll;
                break;
            }



        }

        System.out.println(result);



    }
}
