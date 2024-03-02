import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Main {
    static int[] map;
    static int N;
    static long useEnergy(int i, int j) {
        return (long) (j - i) * (1 + abs(map[i] - map[j]));
    }
    static boolean canGo(long mid) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        boolean[] visited = new boolean[N+1];
        while(!q.isEmpty()) {
            int now = q.poll();
            if(now == N) {
                return true;
            }
            long limit = Math.min(N, now+mid+1);
            for(int i = now+1;i<=limit;i++) {
                if(!visited[i]&&useEnergy(now,i)<=mid) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        map = new int[N+1];
        for(int i= 1;i<=N;i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        long lo = 0, hi = Integer.MAX_VALUE;
//K의 최대 = 1000000 * 5000-
        while(lo+1<hi) {
            long mid = (hi-lo)/2 + lo;
            boolean temp = canGo(mid);
//            System.out.println("temp = " + temp);
            if(temp) {
                hi = mid;
            }
            else {
                lo = mid;
            }

        }

        System.out.println(hi);

    }

}
