import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int max = 0;
        int[] ary = new int[N];

        for(int i= 0;i<N;i++) {
            int temp = Integer.parseInt(br.readLine());
            max = Math.max(max, temp);
            ary[i] = temp;
        }

        long sum = max - ary[0];

        for (int i = 0; i < N - 1; i++) {
            if (ary[i] < ary[i + 1]) {

            }
            if (ary[i] > ary[i + 1]) {
//                sum += max - ary[i];
//                sum+=1;
                sum += ary[i] - ary[i + 1];// + (max - ary[i]);
            }
//            else {
//
//            }
        }
        System.out.println(sum);
    }
}
