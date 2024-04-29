import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int N = Integer.parseInt(br.readLine());
        long[] a = new long[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i= 1;i<=N;i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합 이용
        for(int i= 1;i<=N;i++) {
            a[i]+=a[i-1];
        }

        int M = Integer.parseInt(br.readLine());
        long[] b = new long[M+1];
        st = new StringTokenizer(br.readLine());

        for(int i= 1;i<=M;i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        for(int i= 1;i<=M;i++) {
            b[i]+=b[i-1];
        }


        List<Long> A = new ArrayList<>();
        for(int i = N;i>=0;i--) {
            for(int j= 0;j<i;j++) {
                A.add(a[i] - a[j]);
            }
        }

        List<Long> B = new ArrayList<>();
        for(int i = M;i>=0;i--) {
            for(int j= 0;j<i;j++) {
                B.add(b[i] - b[j]);
            }
        }

        Collections.sort(A, Collections.reverseOrder());
        Collections.sort(B);

//        System.out.println("A = " + A);
//        System.out.println("B = " + B);
        int i = 0;
        long sum = 0;
        while(true) {
            if(i==A.size()) {
                break;
            }

            int lB = lowerBound(T - A.get(i), B.size(), B);
            int uB = upperBound(T - A.get(i), B.size(), B);

//            System.out.println(i);
//            System.out.println("uB = " + uB);
//            System.out.println("lB = " + lB);
            if(lB<B.size()&&B.get(lB)==T-A.get(i)) {
                sum += (uB - lB+1);
            }
            i++;
        }
        System.out.println(sum);

        //약 100만 개 배열 두 개가 있을 때, 하나씩 포인터 이동

    }

    static int lowerBound(long bValue, int bSize, List<Long> B) {

        int lo = -1, hi = bSize;

        while(lo+1<hi) {
            int mid = (hi-lo)/2 + lo;
            if(B.get(mid) >= bValue) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return hi;

    }
    static int upperBound(long bValue, int bSize, List<Long> B) {
        int lo = 0, hi = bSize;

        while(lo+1<hi) {
            int mid = (hi-lo)/2 + lo;
            if(B.get(mid) > bValue) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return lo;
    }
}


// 0 1 4 5 7

// 7

//5
//4
//-1 -3 1 2
//3
//1 3 2