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
        for(int i= 0;i<N;i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ary);
        int l = 0, r = N-1;
        int sum = 0;
        while(l<r) {

            sum+=(2*ary[r]);

            l++;
            r--;

        }
        if(r==l) {
            sum+= ary[r];
        }
        System.out.println(sum);

    }
}