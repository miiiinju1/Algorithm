import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static class Phase {
        int a,b,c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Phase phase = (Phase) o;

            if (a != phase.a) return false;
            if (b != phase.b) return false;
            return c == phase.c;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            result = 31 * result + c;
            return result;
        }

        public Phase(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    static Phase moving (int i,int j, Phase now) {
        int[] abc = new int[3];
        abc[0] = now.a;
        abc[1] = now.b;
        abc[2] = now.c;

        int max = Math.min(ABC[i], abc[i] + abc[j]);
        int diff = max - abc[i];
        abc[i]+=diff;
        abc[j]-=diff;
        return new Phase(abc[0], abc[1], abc[2]);
    }
    static int[] ABC;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        ABC = new int[3];
        ABC[0] = A;
        ABC[1] = B;
        ABC[2] = C;
        Phase start = new Phase(0, 0, C);

        HashSet<Phase> visited = new HashSet<>();
        ArrayDeque<Phase> q = new ArrayDeque<>();

        q.add(start);
        HashSet<Integer> list = new HashSet<>();

        while(!q.isEmpty()) {
            Phase now = q.poll();
            if (visited.contains(now)) {
                continue;
            }
            visited.add(now);
            if(now.a==0)
                list.add(now.c);

            //C->A
            Phase temp = moving(2,0, now);
            if(!visited.contains(temp)) {
                q.add(temp);
            }
            temp = moving(2,1, now);
            if(!visited.contains(temp)) {
                q.add(temp);
            }
            //C->B
            //B->A
            temp = moving(1,0, now);
            if(!visited.contains(temp)) {
                q.add(temp);
            }
            //B->C
            temp = moving(1,2, now);
            if(!visited.contains(temp)) {
                q.add(temp);
            }
            temp = moving(0,1, now);
            if(!visited.contains(temp)) {
                q.add(temp);
            }
            //A->C

            temp = moving(0,2, now);
            if(!visited.contains(temp)) {
                q.add(temp);
            }
            //A->B



        }

        StringBuilder sb = new StringBuilder();
        list.stream().sorted().forEach(i -> sb.append(i + " "));
        System.out.println(sb);


    }
}
