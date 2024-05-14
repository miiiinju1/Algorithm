import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer, Integer> window = new HashMap<>();

        long before = N;
        window.put(Integer.parseInt(st.nextToken()), 0);
        int lastIndex = -1;
        for (int i = 1; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());

            if(window.containsKey(now)) {
                lastIndex = Math.max(lastIndex, window.get(now));
                window.replace(now, i);
            }
            else {
                window.put(now, i);
            }

//            System.out.println("lastIndex = " + lastIndex);
//            System.out.println(before);
//            if(i-lastIndex!=1)
            before = before + (i - lastIndex - 1);

        }
//        for(int i= 0;i<N;i++) {
//            System.out.print(dp[i]+" ");
//        }
//        System.out.println();
        System.out.println(before);


    }
}
