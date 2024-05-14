
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

//        Map<Integer, Integer> window = new HashMap<>();

        long before = N;
        int[] beforeAry = new int[100001];
        Arrays.fill(beforeAry, -1);
        beforeAry[Integer.parseInt(st.nextToken())] = 0;

//        window.put(Integer.parseInt(st.nextToken()), 0);
        int lastIndex = -1;
        for (int i = 1; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());


            if(beforeAry[now]!=-1) {
                lastIndex = Math.max(lastIndex, beforeAry[now]);
            }
            beforeAry[now] = i;

//            window.put(now, i);

            before = before + (i - lastIndex - 1);

        }

        System.out.println(before);


    }
}
