
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] even = new int[N / 2];
        int[] odd = new int[N / 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N / 2; i++) {
            even[i] = Integer.parseInt(st.nextToken());
            odd[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N / 2; i++) {
            even[i] += even[i - 1];
            odd[i] += odd[i - 1];
        }

        int max = odd[N / 2 - 1];

        for (int i = 0; i < N / 2; i++) {
            int e = even[i];
            int o = odd[N / 2 - 1] - odd[i];
            max = Math.max(max, e + o);
        }

        if(N==2) {
            System.out.println(max);
            return;
        }
        int last = odd[N / 2 - 1] - odd[N / 2 - 2];
        for (int i = 0; i < N / 2; i++) {
            int e = even[i];

            int o = odd[N / 2 - 1] - last;

            if(i-1>=0) {
                o -= odd[i-1];
            }
            max = Math.max(max, e + o);
        }
        System.out.println(max);
    }
}
