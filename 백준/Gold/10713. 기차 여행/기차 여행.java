import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Plan {
        int from, to;

        public Plan (int from, int to) {
            this.to = to;
            this.from = from;
        }

    }
    static class Train {
        int a,b,c;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Train{");
            sb.append("a=").append(a);
            sb.append(", b=").append(b);
            sb.append(", c=").append(c);
            sb.append('}');
            return sb.toString();
        }

        public Train(int a, int b , int c) {
            this.a=  a;
            this.b = b;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Plan> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int temp = Integer.parseInt(st.nextToken());
        for(int i= 1;i<M;i++) {
            int next = Integer.parseInt(st.nextToken());
            list.add(new Plan(temp, next));
            temp = next ;
        }
        long[] map = new long[N];
        for (Plan plan : list) {
            int min = Math.min(plan.from, plan.to);
            int max = Math.max(plan.from, plan.to);
            map[min]+=1;
            if(max<N)
                map[max]-=1;
        }

        Train[] trains = new Train[N];
        for(int i=1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            trains[i] = new Train(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
//            System.out.println("trains[i] = " + trains[i]);
        }

        //1, 2, 2, 2, 3,
        //1 3 1


        for(int i=1;i<N;i++) {
            map[i]+=map[i-1];
        }
//        for (int i : map) {
//            System.out.print(i+" ");
//        }
//        System.out.println();

        long sum = 0;
        for(int i= 1;i<N;i++) {
            long times = map[i];
            if(times>=0) {
                Train t = trains[i];
                sum += Math.min(t.a * times, t.b * times + t.c);
            }

        }
        System.out.println(sum);

    }
}

// O - O - O - O - O - O - O

//도시 i에서 i+1로 갈 때
//철도 i를 이용


//