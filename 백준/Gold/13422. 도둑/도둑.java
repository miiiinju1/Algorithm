
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < T; tc++) {
      var st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());

      int M = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[] ary = new int[N];

      st = new StringTokenizer(br.readLine());
      for(int i = 0;i<N;i++) {
        ary[i] = Integer.parseInt(st.nextToken());
      }
      int lo = -1, hi = M-1;

      int count = 0;
      int temp = 0;
      for (int i = 0; i < M; i++) {
        temp += ary[i];
      }
      if(N==M) {
        if(temp<K) {
          count++;
        }
        bw.write(count + "\n");
        continue;
      }
      for (int i = 0; i < N; i++) {
        if(temp < K) {
          count++;
        }
        hi = (hi + 1) % N;
        lo = (lo + 1) % N;
        temp = temp + ary[hi]- ary[lo];

      }
      bw.write(count + "\n");

    }
    bw.flush();bw.close();
  }

}
