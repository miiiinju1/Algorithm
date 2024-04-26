
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] ary;

    static long[] search(long[] count, int index) {

        if(index== N) {
            return count;
        }
        long[] temp = new long[H+1];

//        for (Integer num : ary[index]) {
//            temp[num] += 1;
//        }
        for(int i = 0;i<=H;i++) {
            if(count[i]!=0) {
                for (Integer num : ary[index]) {
                    if(i+num<=H) {
                        temp[i+num] = (temp[i+num]+count[i])%10007;
                    }
                }
            }
        }
        return search(temp, index + 1);
    }

    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
         H = Integer.parseInt(st.nextToken());

        ary = new List[N];
        for(int i = 0;i<N;i++) {
            ary[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            ary[i].add(0);
            while(st.hasMoreTokens()) {
                ary[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        long[] initial = new long[H+1];
        for (Integer i : ary[0]) {
            initial[i] += 1;
        }

        final long[] search = search(initial, 1);

        System.out.println(search[H]);

    }
}
