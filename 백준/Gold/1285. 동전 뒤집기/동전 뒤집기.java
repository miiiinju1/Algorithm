
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

   
    static int N;
    static int min;

    static void find(int[] mask, int row) {

        min = Math.min(min,count(mask));

        for (int i = row+1; i < N; i++) {
                mask[i] = mask[i] ^ (1 << N) - 1;
                find(mask, i);
                mask[i] = mask[i] ^ (1 << N) - 1;
        }

    }
    static Set<Integer> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());

        int[] init = new int[N];
        for (int i = 0; i < N; i++) {
            final String str = br.readLine();

            int now = 0;

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'H') {
                    now += (1 << j);
                }
            }
            init[i] = now;
        }

        visited = new HashSet<>();
        min = Integer.MAX_VALUE;
        find(init,-1);


        System.out.println(min);





    }

    private static int count(int[] mask) {
        int result = 0;
        for(int j= 0;j<N;j++) {
            int temp = 0;
            for(int i = 0;i<mask.length;i++) {
                int bit = mask[i];
                if ((bit & (1 << j)) == 0) {
                    temp += 1;
                }
            }
            result += Math.min(N - temp, temp);
        }
        return result;


    }

}
