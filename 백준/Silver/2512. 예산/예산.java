import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] budget;
    static int N,M;

    static boolean Check(int request)
    {
        long sum = 0;
        for(int i =0;i<N;i++)
        {
            if(request>=budget[i])
                sum+=budget[i];
            else
                sum += request;
        }

        return M<sum;
    }
    // TTTTTFFFF
    static int binarySearch(int lo, int hi)
    {
        while(lo+1<hi)
        {
            int mid = lo + (hi-lo)/2;
            if(Check(mid))
                hi = mid;
            else
                lo = mid;



        }
        return lo;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        budget = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max =0;
        for(int i=0;i<N;i++)
        {
            budget[i] = Integer.parseInt(st.nextToken());
            if(max<budget[i])
                max = budget[i];
        }

        M = Integer.parseInt(br.readLine());
        System.out.println(binarySearch(1,max+1));






    }
}
