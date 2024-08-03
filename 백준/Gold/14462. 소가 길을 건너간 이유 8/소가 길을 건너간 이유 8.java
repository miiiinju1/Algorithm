
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] ary1 = new int[N];
        for(int i = 0;i<N;i++) {
            ary1[i] = Integer.parseInt(br.readLine());
        }

        int[] ary2 = new int[N];
        for(int i= 0;i<N;i++) {
            ary2[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = N-1; j >=0 ; j--) {
                if (!isValid(ary1[i], ary2[j])) continue;


                for (int z = j-1; z >=0; z--) {
                    if (dp[j] < dp[z] + 1) {
                        dp[j] = dp[z] + 1;
                    }
                }if(dp[j] < 1) {
                    dp[j] = 1;
                }
            }
//            for(int m = 0;m<N;m++) {
//                System.out.print(dp[m]+" ");
//            }
//            System.out.println();


        }

        int max = 0;
        for(int i= 0;i<N;i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }
    private static boolean isValid(int a, int b) {
        return Math.abs(a - b) <= 4;
    }
}
