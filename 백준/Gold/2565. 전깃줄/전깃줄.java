import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[501];
        int[] B = new int[501];
        for(int i =0;i<N;i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            A[a] = b;
            B[b] = a;
        }
        int max = 0;
        int[] dp = new int[501];
        int[] dp2 = new int[501];
        for (int i = 0; i < 501; i++) {
            for (int j = i; j >= 0; j--) {
                if (A[i] > A[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
                if (B[i] > B[j] && dp2[i] < dp2[j] + 1) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }
        int temp = Arrays.stream(dp).max().getAsInt();
        int temp2 = Arrays.stream(dp2).max().getAsInt();
        max = Math.max(max, Math.max(temp, temp2));

        System.out.println(N-max);
    }
}

//