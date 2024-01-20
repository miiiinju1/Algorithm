
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long binarySearch(int index) {
        int lo = index, hi = N;
        long value = ink[index];
        while(lo+1<hi) {
            int mid = (hi-lo)/2 + lo;

//            System.out.println(mid+" viscosity = " + viscosity[mid]);
            if(viscosity[mid]>value) {
                hi = mid;
            }
            else {
                lo = mid;
            }




        }
        return lo-index;


    }
    static int N;
    static long[] ink;
    static long[] viscosity;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

         ink = new long[N];
         viscosity = new long[N];
        for(int i = 0;i<N;i++) {
            ink[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0;i<N;i++) {
            viscosity[i] = Long.parseLong(st.nextToken());
        }

        for(int i = 0;i<N;i++) {

            bw.write(binarySearch(i)+" ");

        }

bw.flush();bw.close();
    }
}
