
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] ary = new long[1000001];
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            ary[x] += g;
        }

        long sum = 0;
        long max = 0;
        int i;
        if(1000000<=K) {
            for(i= 0;i<=1000000;i++) {
                sum+=ary[i];
            }
            System.out.println(sum);
            return ;
        }
        for(i= 0;i<=K;i++) {
            sum += ary[i];
        }
//
//        System.out.println("sum = " + sum);
        for(i = 0;i+K<1000000;i++) {
            sum += ary[i + K+1];
            if(i-K-1>=0)
                sum -= ary[i-K-1];
//            System.out.println("ary[i+K] = " + ary[i+K]);
//            System.out.println("sum = " + sum);
//            System.out.println("sum = " + sum);
            max = Math.max(sum, max);

        }
        System.out.println(max);
    }
}

//4 499999
//4 7
//10 15
//2 2
//100 1000000
//
//4 15
//4 7
//10 15
//2 2
//5 1


//5 2 4 10

//