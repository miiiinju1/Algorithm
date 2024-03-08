import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int[] slice;
    static int N,M,L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
         L = Integer.parseInt(st.nextToken());

        slice = new int[M];
        for(int i = 0;i<M;i++) {
            slice[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        for(int i= 0;i<N;i++) {
            sb.append(sliceMax(Integer.parseInt(br.readLine())))
                    .append("\n");

        }

        System.out.println(sb);

    }

    private static int sliceMax(int count) {
        int lo = 0,hi = 4000001;
        while(lo+1<hi) {
            int mid = (hi-lo)/2+lo;
//            System.out.printf("check(%d) = %d \n",mid,check(mid));
            if(check(mid)>=count) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }
        return lo;
    }
    private static int check(int mid) {
        int now = 0;
        int nowCount = 0;
        for(int i= 0;i<M;i++) {
            if(slice[i]>=now+mid) {
                nowCount++;
                now = slice[i];
            }

        }
        if(L-now<mid) {
            nowCount--;
        }
        return nowCount;



    }
}
