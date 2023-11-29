
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] ary = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                ary[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(ary);
            int result = 0;
            for (int i = 0; i < N-2; i += 2) {
                result = Math.max(result, Math.abs(ary[i] - ary[i + 2]));
            }
            for (int i = 1; i < N-2; i += 2) {
                result = Math.max(result, Math.abs(ary[i] - ary[i + 2]));
            }
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
}
