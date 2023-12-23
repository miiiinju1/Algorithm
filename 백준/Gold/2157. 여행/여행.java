import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Airport implements Comparable<Airport> {

        int next, value;

        public Airport(int next, int value) {

            this.next = next;
            this.value = value;
        }

        @Override
        public int compareTo(Airport o) {
            return o.value - this.value;
        }
    }

    static HashMap<Integer, PriorityQueue<Airport>> map = new HashMap<>();

    static int N,M,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 1;i<=N;i++) {
            map.put(i, new PriorityQueue<>());
        }
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a<b) {  //서 동 항로는 거르기
                map.get(a).add(new Airport(b, c));
            }
        }
        long[][] dp = new long[M][N+1];

        for(int i = 1;i<=N;i++) {
            for (Airport airport : map.get(i)) {
                for(int m = 0;m<M-1;m++) {
                    if(i==1||dp[m][i]!=0) {
                        dp[m + 1][airport.next] = Math.max(dp[m][i] + airport.value, dp[m + 1][airport.next]);
                    }
                }
            }
        }
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int m =0;m<M;m++) {
            pq.add(dp[m][N]);
        }
        System.out.println(pq.poll());
    }
}
