
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //6 1 5 2 4 3  = 6 0 4 0 2
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] ary =new Integer[N];
        for(int i= 0;i<N;i++)
            ary[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(ary, Collections.reverseOrder());

        long sum =ary[0];
        int i=1,j=N-1;
        for(;i<j;i++,j--)
        {
            sum += Math.max(0, ary[j] - ary[i - 1]);
            sum += Math.max(0, ary[i] - ary[j]);
        }
        if (i == j)
            sum += Math.max(0, ary[i]-ary[i - 1]);
        System.out.println(sum);
    }
}
