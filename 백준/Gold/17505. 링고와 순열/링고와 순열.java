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
        int putCount = N-1;
        int[] ary = new int[n];

        long now=0;

        int leftIndex=0;
        while(N>0) {
            //남은게 더 더해질 수 있는 것 보다 많으면
            if(K-now>putCount) {
                ary[leftIndex++] = N--;
                now+=putCount--;
            }
            else if(K-now<putCount) {
                ary[(n-1)-(int)(K-now)] = N--;
                break;
            }
            else {
                ary[leftIndex] = N--;
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
