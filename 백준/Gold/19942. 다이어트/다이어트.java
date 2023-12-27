import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static class Ingredient {
        int p,f,s,v,cost;
        public Ingredient(int p, int f, int s, int v,int cost) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.cost = cost;
        }
        public void add(Ingredient ingredient) {
            this.p += ingredient.p;
            this.f += ingredient.f;
            this.s += ingredient.s;
            this.v += ingredient.v;
            this.cost +=ingredient.cost;
        }
        public void minus(Ingredient ingredient) {
            this.p -= ingredient.p;
            this.f -= ingredient.f;
            this.s -= ingredient.s;
            this.v -= ingredient.v;
            this.cost -= ingredient.cost;
        }
        public Ingredient() {
            this.p = 0;
            this.f = 0;
            this.s = 0;
            this.v = 0;
            this.cost = 0;
        }

        public boolean isSatisfied() {
            return this.p >= mp &&
                    this.f >= mf &&
                    this.v >= mv &&
                    this.s >= ms && min>=this.cost;
        }
    }
    static Ingredient[] ingredients;
    static int N;

    static HashSet<String> set = new HashSet<>();
    static Ingredient now;
    static int min;
    static int[] choice;
    static void combination(int start, int index, int target) {
        if(index == target) {

           if(now.isSatisfied()) {
               if(min != now.cost) {
                   set = new HashSet<>();
               }
               StringBuilder sb = new StringBuilder();
               for(int i = 0;i<target;i++) {
                   sb.append(choice[i]+1).append(" ");
               }
               set.add(sb.toString());

               min = now.cost;
           }
            return ;
        }
        if(index==0) {
            now = new Ingredient();
        }

        for(int i = start;i<N;i++) {
            choice[index] = i;
            now.add(ingredients[i]);
            combination(i+1, index + 1, target);
            now.minus(ingredients[i]);
        }

    }

    static int mp,mf,ms,mv;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ingredients = new Ingredient[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            Ingredient ingredient =
                    new Ingredient(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    );
            ingredients[n] = ingredient;
        }


        min = Integer.MAX_VALUE;
        for(int n = 1;n<=N;n++) {
            choice = new int[n];
            combination(0,0,n);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return ;
        }
        System.out.println(min);
        set.stream().min(String::compareTo).ifPresent(System.out::println);
    }
}