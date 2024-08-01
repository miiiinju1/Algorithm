
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int process(int i, int j) {
        if(i==j) {
            return 0;
        }

//        System.out.println("i+\" \"+j = " + i+" "+j);
        int before = i;
        int sum = 0;

        int min = Integer.MAX_VALUE;
        for(int x = i;x<j;x++) {
            min = Math.min(Math.abs(diff[x]), min);
        }

        for (int x = i; x < j; x++) {
            if(diff[x] <0) {
                diff[x] += min;

            }
            else if(diff[x]>0) {
//                System.out.println("diff[x] = " + diff[x]);
                diff[x] -= min;
//                System.out.println("diff[x] = " + diff[x]);
            }
        }
//        for(int x = i;x<j;x++) {
//            System.out.print(diff[x]+" ");
//        }
//        System.out.println();
//
//        System.out.println("min = " + min);
//        if (min == Integer.MAX_VALUE) {
//            return 0;
//        }
        sum += min;
        for (int x = i; x < j; x++) {
            if(diff[x]==0) {
                sum += process(before, x);
                before = x+1;
            }
        }
        sum += process(before, j);


//        System.out.println("min = " + min);

        return sum;

    }

        static int[] diff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] ary = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = Integer.parseInt(st.nextToken()) - ary[i];
        }

        boolean isBeforePositive = false;
        int beforeMax = 0;

        isBeforePositive = diff[0] < 0 ? false : true;
        beforeMax = Math.abs(diff[0]);
        long sum = 0L;
        int beforeIndex = 0;

        for (int i = 1; i < N; i++) {
            if(diff[i] > 0 && !isBeforePositive) {
                // 음수부
                sum += process(beforeIndex, i);
                beforeIndex = i;
                isBeforePositive = true;
            }
            else if(diff[i] < 0 && isBeforePositive) {
                // 양수부
                sum += process(beforeIndex, i);
                beforeIndex = i;
                isBeforePositive = false;
            }

//            beforeMax = Math.max(beforeMax, Math.abs(diff[i]));
        }
        sum += process(beforeIndex, N);
//        sum += beforeMax;


        System.out.println(sum);

    }
}
