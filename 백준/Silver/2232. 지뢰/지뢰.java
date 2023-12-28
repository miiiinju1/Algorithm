import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] ary = new int[N+2];
        ary[0] = 0;
        ary[N+1]=0;
        for(int i = 1;i<=N;i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i =1;i<=N;i++) {
            if(ary[i]>=ary[i-1]&&ary[i]>=ary[i+1]) {
                pq.add(i);
            }
        }
        while(!pq.isEmpty())  {
            bw.write(pq.poll() + "\n");
        }
        bw.flush();bw.close();
    }

}
