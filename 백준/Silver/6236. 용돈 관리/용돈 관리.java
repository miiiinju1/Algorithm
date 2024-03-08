import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] ary;
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        ary = new int[N];
        int max = 0;
        for(int i= 0;i<N;i++) {
            ary[i] = Integer.parseInt(br.readLine());
            max = Math.max(max,ary[i]);
        }

        int lo = max-1, hi = 1000000001;

        while(lo+1<hi) {
            int mid = (hi-lo)/2+lo;

            if(check(mid)<=M) {
                hi = mid;
            }
            else {
                lo = mid;
            }

        }
        System.out.println(hi);


    }
    private static int check(int mid) {
        //일단 최대한 적게 인출하는 개수로

        int now = mid;
        int count = 1;
        for(int i= 0;i<N;i++) {
            if(now-ary[i]>=0) {
                now-=ary[i];
            }
            else {
                now = mid-ary[i];
                count++;
            }

        }

        return count;
    }
}
/*
*
*
*
* 1번 빼는 경우
*
* 5
*
*
*
* 5번
*
*
* 100
* 400
*
* 300
* 100
*
* 100
*
* 101
*
* 400
*
*
*
*
* */

//7 7
//100
//400
//300
//100
//500
//101
//400