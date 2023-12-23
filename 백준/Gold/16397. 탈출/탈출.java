import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static class Phase {
        int n, depth;

        public Phase(int n, int depth) {
            this.n = n;
            this.depth = depth;
        }
    }

    static int T,target;

    static int buttonA(int N) {
        N++;
        if(N>99999) {
            return -1;
        }
        return N;
    }
    static int buttonB(int N) {
        N*=2;
        if(N==0) {
            return 0;
        }
        else if(N>99999) {
            return -1;
        }
        else if(N>=10000) {
            N-=10000;

        }
        else if (N>=1000) {
            N-=1000;
        }
        else if(N>=100) {
            N-=100;
        }
        else if(N>=10) {
            N-=10;
        }
        else {
            N-=1;
        }
        return N;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
       int N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        ArrayDeque<Phase> q = new ArrayDeque<>();
        q.add(new Phase(N, 0));

        HashSet<Integer> visited = new HashSet<>();
        while(!q.isEmpty()) {
            Phase now = q.poll();
//            System.out.println("now = " + now);

            if (visited.contains(now.n))
                continue;
            if(now.n == target) {
                System.out.println(now.depth);
                return ;
            }

             visited.add(now.n);




             if(now.depth+1<=T) {
                 int temp = buttonA(now.n);
                 if (temp != -1) {
                     if (!visited.contains(temp)) {
                         q.add(new Phase(temp, now.depth + 1));
                     }
                 }
                 temp = buttonB(now.n);
                 if (temp != -1) {
                     if (!visited.contains(temp)) {
                         q.add(new Phase(temp, now.depth + 1));

                     }
                 }
             }

        }
        System.out.println("ANG");

    }
}
