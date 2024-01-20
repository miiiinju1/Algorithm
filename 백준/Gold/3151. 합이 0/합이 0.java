
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


//baekjoon3151
public class Main {
    static int[] ary;
    static int N;
    static int search(int index, int val) {
        int value = -1*val;
        int lo = index, hi = N;
        
        while(lo+1<hi) {
            int mid = (hi-lo)/2+lo;
            
            if(ary[mid]>value) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }
        
        int highest = hi;
        lo = index;
        hi = N;
        while(lo+1<hi) {
            int mid = (hi-lo)/2+lo;

            if(ary[mid]>=value) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        int lowest = hi;

        if(highest<=lowest) {
            return 0;
        }
//        System.out.println("highest = " + highest);
//        System.out.println("lowest = " + lowest);
//        //6 5 4 3 2 1
        // 20
        return highest-lowest;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());

        ary =new int[N];
        long count = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ary);
        for(int a = 0;a<N;a++) {
            for(int b = a+1;b<N;b++) {
               int temp = ary[a]+ary[b];
               count += search(b, temp);

            }
        }
        System.out.println(count);

    }
}
// -10000 ~ 10000사이

//코딩 실력 합이 0??
