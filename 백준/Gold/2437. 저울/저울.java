import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ary = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ary);

        long now=ary[0];
        if(ary[0]>1) {
            System.out.println(1);
            return ;
        }
        for(int i= 1;i<N;i++) {
            long test = now+ary[i];

            if(now*2>=test) {
                now = test;
            } else if(ary[i] == now+1 ) {
                now = test;
            }
            else {
                System.out.println(now+1);
                return ;
            }
        }
        System.out.println(now+1);
    }
}
