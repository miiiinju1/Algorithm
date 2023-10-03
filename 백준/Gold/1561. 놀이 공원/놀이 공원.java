
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] map;
    static int N,M;

    static int leftCheck(long val)
    {
        int index=0;
        long count =0;
        for (int i = 0; i < M; i++) {
            count += ((val-1) / map[i] + 1);
        }
        long left = (long)N-count;
        for(int i=0;i<M;i++)
        {
            if(left==0)
                return index+1;
            if(val%map[i]==0) {
                left--;
                index = i;
            }
        }
        return index+1;
    }
    static boolean Check(long mid) {
        long count = 0;
        for(int i=0;i<M;i++)
        {
            count+=(mid/map[i]+1);
        }
        if(count<N)
            return true;
        return false;
    }
    static int binarySearch(long lo, long hi)
    {
        while(lo+1<hi)
        {
            long mid = lo + (hi-lo)/2;

            if (Check(mid))
                lo = mid;
            else
                hi = mid;
        }
        return leftCheck(hi);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        map = new int[M];
        for (int i = 0; i < M; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        if(N<=M)
            System.out.println(N);

        else
          System.out.println(binarySearch(0, 90000000000L));

    }

}
