
import java.io.*;
import java.util.ArrayList;
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
            ArrayList<Integer> list = new ArrayList<>();

            list.add(ary[0]);
            list.add(ary[1]);
            int result = Math.abs(ary[0] - ary[1]);
            int minDiff = Integer.MAX_VALUE;
            for(int i=2;i<N;i++) {
                int now = ary[i];
                minDiff = Integer.MAX_VALUE;
                int index = 0;
                int max = 0;
                int length = list.size();
                for (int j = 0; j < length - 1; j++) {
                    int left = Math.abs(list.get(j) - now);
                    int right = Math.abs(list.get(j+1) - now);
                    int temp = left + right;
                    if (temp < minDiff) {
                        minDiff = temp;
                        max = Math.max(left, right);
                        index = j+1;
                    }
                    if (minDiff != Integer.MAX_VALUE && temp > minDiff) {
                        break;
                    }


                }
                result = Math.max(max,result);
                list.add(index, now);
            }
            bw.write( result+"\n");
            }

        bw.flush();
        bw.close();
    }
}
