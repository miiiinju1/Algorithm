
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][][] dp;
    static int N;
    static final int MOD = 1_000_000_000;

    static int[] ary;
    static int search(int index, int before, int used) {
        
        if (index == N) {
            return used == 2046 ? 1 : 0;
        }
        
        if (dp[used][before][index] != -1) {

            return dp[used][before][index];
        }

        int temp = 0;

        if (before - 1 >= 0) {
            ary[index] = before - 1;
            temp = (temp + search(index + 1, before - 1, used | 1 << ((before - 1) + 1)) % MOD) % MOD;
        }

        if (before + 1 < 10) {
            ary[index] = before + 1;
            temp = (temp + search(index + 1, before + 1, used | 1 << ((before + 1) + 1)) % MOD) % MOD;

        }
        
        dp[used][before][index] = temp;
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[(1 << 11) - 1][10][N];
        ary = new int[N];
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < 10; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int temp = 0;
        for(int i = 1;i<10;++i) {
            ary[0] = i;
            temp = (temp + search(1, i, 1 << (i+1)) % MOD) % MOD;
        }
        
        System.out.println(temp);

    }

}
