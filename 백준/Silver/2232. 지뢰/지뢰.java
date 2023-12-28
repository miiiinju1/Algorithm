import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] ary = new int[N+2];
        for(int i = 1;i<=N;i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }
        for(int i =1;i<=N;i++) {
            if(ary[i]>=ary[i-1]&&ary[i]>=ary[i+1]) {
                bw.write(i+"\n");
            }
        }
        
        bw.flush();bw.close();
    }

}
