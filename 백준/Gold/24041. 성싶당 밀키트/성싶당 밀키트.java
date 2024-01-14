
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Ingredient {
        int S,L,O;

        public Ingredient(int s, int l, int o) {
            S = s;
            L = l;
            O = o;
        }
    }
    static Ingredient[] ingredients;
    static int N,G,K;

    static boolean Check(long mid) {

        long day = mid;
        long countG = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i= 0;i<N;i++) {
            final Ingredient ingredient = ingredients[i];

            long temp = ingredient.S * Math.max(1, day - ingredient.L);
            countG += temp;

           if(ingredient.O==1)
               pq.add(temp);

        }

        for(int i= 0;i<K;i++) {
            if(pq.isEmpty()) {
                break;
            }
            countG-= pq.poll();
        }

        if(countG<=G) {
            return true;
        }
        return false;



    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         G = Integer.parseInt(st.nextToken());
         K = Integer.parseInt(st.nextToken());

        ingredients = new Ingredient[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());

            ingredients[i] = new Ingredient(S, L, O);
        }

        long lo = 0,hi = Integer.MAX_VALUE;
        while(lo+1<hi) {
            long mid = (hi-lo)/2 + lo;

            if(Check(mid)) {
                lo = mid;
            }
            else {
                hi = mid;
            }

        }

        System.out.println(lo);


        //

        //

        //

        //

        //

        //

        //

    }

}
