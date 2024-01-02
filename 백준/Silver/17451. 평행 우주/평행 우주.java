import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;
        for(int i = N-1;i>=0;i--) {
            if(sum<map[i]) {
                sum = map[i];
            }
            else {
                int temp = map[i];
                long 몫= sum/temp;
                if(sum%temp!=0)
                    몫++;
                sum = (몫)*temp;
            }
        }
        System.out.println(sum);

    }
}
