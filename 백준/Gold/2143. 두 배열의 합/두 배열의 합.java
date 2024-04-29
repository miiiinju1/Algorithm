import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int N = Integer.parseInt(br.readLine());
        long[] a = new long[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i= 1;i<=N;i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 누적합 이용
        for(int i= 1;i<=N;i++) {
            a[i]+=a[i-1];
        }

        int M = Integer.parseInt(br.readLine());
        long[] b = new long[M+1];
        st = new StringTokenizer(br.readLine());

        for(int i= 1;i<=M;i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        for(int i= 1;i<=M;i++) {
            b[i]+=b[i-1];
        }

        Map<Long, Long> aCount = new HashMap<>();
        Map<Long, Long> bCount = new HashMap<>();

        for(int i = N;i>=0;i--) {
            for(int j= 0;j<i;j++) {
                long temp = a[i] - a[j];
                aCount.put(temp, aCount.getOrDefault(temp, 0L) + 1);
            }
        }
        for(int i = M;i>=0;i--) {
            for(int j= 0;j<i;j++) {
                long temp = b[i] - b[j];
                bCount.put(temp, bCount.getOrDefault(temp, 0L) + 1);
            }
        }


        final long sum = aCount.entrySet().stream()
                .filter(ent -> bCount.containsKey(T - ent.getKey()))
                .mapToLong(ent -> (ent.getValue() * bCount.get(T - ent.getKey())))
                .sum();

        System.out.println(sum);

    }

   
}


// 0 1 4 5 7

// 7

//5
//4
//-1 -3 1 2
//3
//1 3 2