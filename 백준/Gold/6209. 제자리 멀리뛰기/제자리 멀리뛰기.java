
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean Check(int mid) {

        int now = 0;
        int mCount = 0;
        for(int i= 0;i<=n;i++) {
            if(map[i]-now>=mid) {
                now = map[i];
            }
            else {
                mCount++;
//                System.out.print(map[i]+" ");
//                if(mCount>m) {
//                    return false;
//                }
            }
        }
//        System.out.println("|"+mid+" : "+ mCount);
        if(mCount<=m) {
            return true;


        }

        return false;
//        if(mCount<m) {
//            return false;
//        }
//        else if(mCount>m) {
//            return false;
//        }
//        return true;
    }
    static int[] map;
    static int d,n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1];
        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }
        map[n] = d;
        Arrays.sort(map);

        int lo = 1, hi = Integer.MAX_VALUE;

        while(lo+1<hi) {
            int mid = (hi - lo) / 2 + lo;

            if(Check(mid)) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }
        System.out.println(lo);




    }
}

// 2 11 14 17 21
// 2 9 3  3  4    4


// 11 3 3 4 4
//

//11 2 0
//4
//8