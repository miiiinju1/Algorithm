
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Phase {

        int[] mask;

        public Phase(int[] mask) {
            this.mask = mask;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Phase phase = (Phase) o;

            if (!Arrays.equals(mask, phase.mask)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(mask);
        }
    }

    static int N;
    static int min;

    static void find(int[] mask, int row) {

        min = Math.min(min,count(mask));

        for (int i = row+1; i < N; i++) {
                mask[i] = mask[i] ^ (1 << N) - 1;
                if(!visited.contains(Arrays.hashCode(mask))) {
                    visited.add(Arrays.hashCode(mask));
                    find(mask, i);
                }
                mask[i] = mask[i] ^ (1 << N) - 1;
//                find(mask, i);
        }

    }
    static Set<Integer> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());

        int[] init = new int[N];
        for (int i = 0; i < N; i++) {
            final String str = br.readLine();

            int now = 0;

            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'H') {
                    now += (1 << j);
                }
            }

            init[i] = now;
        }

        visited = new HashSet<>();
        min = Integer.MAX_VALUE;
        find(init,-1);

//        System.out.println("min = " + min);
//        visited.add(start.hashCode());

//        System.out.println(count(start.mask));
//        Deque<Phase> q = new ArrayDeque<>();
//        System.out.println("baekjoon1285_동전_뒤집기.main");
//        q.add(start);

//        while(!q.isEmpty()) {
//            final Phase now = q.poll();
////            System.out.println("now = " + now);
//
////            System.out.println("count(now.mask) = " + count(now.mask));
//            min = Math.min(min, count(now.mask));
//
//            // 행
//            for (int i = 0; i < N; i++) {
//
//                final int[] mask = arrayCopy(now.mask);
//
////                mask[i] 를 비트 뒤집기
//                if(!now.flipped[0][i]) {
//
//                    mask[i] = mask[i] ^ (1 << N) - 1;
//
//                    boolean[][] flipped = copyFlip(now.flipped);
//                    flipped[0][i] = true;
//                    Phase next = new Phase(mask, flipped);
//                    if (!visited.contains(next.hashCode())) {
//                        q.add(next);
//                        visited.add(next.hashCode());
//                    }
//
//                }
//
//
//            }
//            // 열
//            for (int i = 0; i < N; i++) {
//
//                final int[] mask = arrayCopy(now.mask);
//
////                mask[i]의 j번째 껄 비트 뒤집기
//
//                for(int j= 0;j<mask.length;j++) {
//                    mask[j] = mask[j] ^ (1 << i);
//
//                }
//                if(!now.flipped[1][i]) {
//
//                    mask[i] = mask[i] ^ (1 << N) - 1;
//
//                    boolean[][] flipped = copyFlip(now.flipped);
//                    flipped[1][i] = true;
//                    Phase next = new Phase(mask, flipped);
//                    if (!visited.contains(next.hashCode())) {
//                        q.add(next);
//                        visited.add(next.hashCode());
//                    }
//
//                }
//            }
//
//
//
//        }


        System.out.println(min);





    }

    private static int count(int[] mask) {
        int result = 0;
        for(int j= 0;j<N;j++) {
            int temp = 0;
            for(int i = 0;i<mask.length;i++) {
                int bit = mask[i];
                if ((bit & (1 << j)) == 0) {
                    temp += 1;
                }
            }
            result += Math.min(N - temp, temp);
        }
        return result;


    }

}
