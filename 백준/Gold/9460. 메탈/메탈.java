
import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int y,x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int[] ary;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            List<Point> list = new ArrayList<>();
            for (int z = 0; z < N; z++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new Point(y, x));
            }
            Collections.sort(list, Comparator.comparingInt(o -> o.x));
            ary = new int[N];

            for (int i = 0; i < N; i++) {
                ary[i] = list.get(i).y;
            }
            final double result = binarySearch(K) / 100.0;
//            System.out.println(i);
            bw.write(String.format("%.1f\n", result));
        }
        bw.flush();bw.close();
    }

    static double binarySearch(int k) {
        long lo = -1, hi = 10000000000L;

        while (lo + 1 < hi) {
            long mid = (hi - lo) / 2 + lo;

//            System.out.println("mid = " + mid);
            int result = check(mid);

            //k가 더 필요한 경우는 너무 적은 거니깐 좀 늘려주기
            if (result > k) {
                lo = mid;
            }
            //k가 덜 필요할 때랑 같을때는 hi줄여서 최대한 줄여보기
            else {
                hi = mid;
            }
        }
//        System.out.println(hi);
        return hi;
    }

    static int check(long m) {

        double mid = (double) m / 100;
        int nowMin = Integer.MAX_VALUE;
        int nowMax = Integer.MIN_VALUE;

        int result = 1;
        for (int i = 0; i < ary.length; i++) {
            nowMin = Math.min(nowMin, ary[i]);
            nowMax = Math.max(nowMax, ary[i]);
//            System.out.println("dd "+ary[i]+" nowMin = "+nowMin+" nowMax = " + nowMax);
            double temp = ((double) (nowMax - nowMin)) / 2;
//            System.out.println("temp = " + temp);
            if (temp > mid) {
                nowMin = ary[i];
                nowMax = ary[i];
//                System.out.println("baekjoon9460_메탈.check");
                result++;
            }
        }
//        System.out.println("mid = " + mid+" "+"result = " + result);
        return result;

    }
}
