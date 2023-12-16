import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int J = Integer.parseInt(br.readLine());

        int lo = 1, hi = M;
        int sum = 0;
        for(int i= 0;i<J;i++) {
            int apple = Integer.parseInt(br.readLine());
            if(hi<apple) {
                int temp = apple - hi;
                sum += temp;
                hi +=temp;
                lo +=temp;
            }
            else if(lo>apple) {
                int temp = lo-apple;
                sum += temp;
                lo-=temp;
                hi-=temp;
            }
        }
        System.out.println(sum);
    }
}


// 1 2 3 4 5
