
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
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ary);

        int lo = 0, hi = 1_000_000_000;

        while (lo + 1 < hi) {
            int mid = (hi - lo) / 2 + lo;

            if (check(ary, mid)) {
                hi = mid;
            } else {
                lo = mid;
            }

        }
        System.out.println(hi);
    }

    // O(n)에 풀어야함

    //counting해두면
    static boolean check(int[] ary, int mid) {
        int[] mod = new int[ary.length];
        for (int i = 0; i < ary.length; ++i) {
            mod[i] = ary[i] / mid;
            if (ary[i] % mid != 0) {
                ++mod[i];
            }
        }


        int hi = mod.length - 1;
        int lo = 0;

        while (lo < hi) {
            int length = mod[hi];
            if(mod[hi] ==1) {
                break;
            }

            int need = length - 2;

            int i;
            for ( i = lo; i < lo + need; ++i) {
                if(mod[i]!=1) {
                    return false;
                }
            }
            lo = i;
            --hi;
        }

        return mod[lo] == 1;


        // 큰거부터 하나씩 1씩 겹치면서 둔다고 했을 떄

        // 각각 n-2개의 단위 1짜리가 필요함

        //그리고 마지막 n, (n>=2)에는 1개짜리가 추가로 하나 더 필요함






    }

}
