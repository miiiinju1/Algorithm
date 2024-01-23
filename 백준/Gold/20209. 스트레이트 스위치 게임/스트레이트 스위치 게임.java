
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Phase {
        int[] now;
int depth;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Phase phase = (Phase) o;

            return Arrays.equals(now, phase.now);
        }

        public Phase(Phase phase) {
            this.now = new int[N+1];
            for(int i= 1;i<=N;i++) {
                this.now[i] = phase.now[i];
            }
            this.depth = phase.depth+1;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(now);
        }

        public Phase(int[] now) {
            this.now = now;
        }

    }

     static Phase click(Phase phase, int switc) {

        Phase newPhase = new Phase(phase);
        for (Integer i : Switch.get(switc)) {
            newPhase.now[i]= (newPhase.now[i]+switc)%5;

        }
        return newPhase;
    }
    static int N;
    static boolean check(Phase phase) {
        int initial = phase.now[1];
        for(int i= 2;i<=N;i++) {
            if(initial!=phase.now[i]) {
                return false;
            }
        }
        return true;

    }
    static HashMap<Integer, ArrayList<Integer>> Switch = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] start = new int[N+1];
        for(int i= 1;i<=N;i++) {
            start[i] = Integer.parseInt(st.nextToken())%5;
        }

        for(int i= 1;i<=K;i++) {
            //스위치
            ArrayList<Integer> temp = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());

            for(int j = 0;j<c;j++) {
                temp.add(Integer.parseInt(st.nextToken()));
            }
            Switch.put(i, temp);
        }
        Phase phase = new Phase(start);

        ArrayDeque<Phase> q = new ArrayDeque<>();
        q.add(phase);

        HashSet<Phase> visited = new HashSet<>();
        visited.add(phase);
        while(!q.isEmpty()) {

            final Phase now = q.poll();

            if(check(now)) {
                System.out.println(now.depth);
                return ;
            }


            for(int i= 1;i<=K;i++) {
                final Phase click = click(now, i);

                if(!visited.contains(click)) {
                    visited.add(click);
                    q.add(click);
                }
            }


        }

        System.out.println(-1);

    }
}
