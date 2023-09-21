
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] lanCable;
    static int K,N;
    static boolean Check(long length)
    {
        long sum = 0;
        for(int i=0;i<K;i++)
        {
            sum+=(lanCable[i]/length);
        }
        return sum>=N;
    }
    //길이
    //TTTTFFFF
    static long binarySearch(long lo, long hi)
    {
        while(lo+1<hi)
        {
            long mid = lo+(hi-lo)/2;

            if(Check(mid))
                lo = mid;
            else
                hi = mid;

        }

        return lo;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lanCable = new int[K];
        for(int i=0;i<K;i++)
            lanCable[i] = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(1,(long)(Math.pow(2,31))));
    }
}
