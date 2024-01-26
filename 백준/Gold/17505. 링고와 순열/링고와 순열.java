import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        int N = n;
        int[] ary = new int[N];

        long now=0;
        int index=n-1;

        int leftIndex=0;
        while(true) {

            if(0==N) {
                break;
            }
            //남은게 더 더해질 수 있는 것 보다 많으면
            if(K-now>N-1) {
                ary[leftIndex++] = N--;
                now+=N;
            }
            else if(K-now<N-1) {
                ary[n-1-(int)(K-now)] = N--;
                break;
            }
            else {
                ary[leftIndex ] = N--;
                break;
            }
        }

        for(int i = n-1;i>=0;i--) {
            if(ary[i]==0) {
                ary[i] = N--;
            }
        }

        for (int i : ary) {
            bw.write(i+" ");
        }
        bw.flush();bw.close();

    }
}
