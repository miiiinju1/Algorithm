import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K,max = 0;
    static int[] ary;

    static void search(int phase, int now) {
        a: for(int i= now;i<N;i++) {
            int temp = 0;
            for(int j= i;j<N;j++) {
                if(temp+ary[j] >= K) {
                    search(phase+(temp+ary[j]-K), j+1);
                    continue a;
                }
                else {
                    temp += ary[j];
                }

            }
//            max = Math.max(max, phase);
        }
        max = Math.max(max, phase);

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());

        ary = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i= 0;i<N;i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

         max = 0;
        search(0, 0);
        System.out.println(max);
    }
}
