import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Phase {
        int n;
        String str;
        public Phase(int n, String str) {
            this.n = n;
            this.str = str;
        }
    }
    static int D (Phase phase) {
        int n =2*phase.n%10000;
        return n;
    }
    static int S (Phase phase) {
        int n =(phase.n-1+10000)%10000;
        return n;
    }
    static int L (Phase phase) {
        int n = phase.n % 1000 * 10 + phase.n/1000;
        return n;
    }
    static int R (Phase phase) {
        int n = phase.n % 10 * 1000 + phase.n/10;
        return n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int targetInt = Integer.parseInt(st.nextToken());
            ArrayDeque<Phase> q = new ArrayDeque<>();
            q.add(new Phase(n, ""));

            boolean[] visited = new boolean[10000];
            while(!q.isEmpty()) {
                Phase now = q.poll();
                if (now.n==targetInt) {
                    bw.write(now.str + "\n");
                    break;
                }
                if(visited[now.n])
                    continue;
                visited[now.n]=true;

                int d = D(now);
                if(!visited[d]) {

                    q.add(new Phase(d, now.str + "D"));
                }
                int s = S(now);
                if(!visited[s]) {
                    q.add(new Phase(s, now.str + "S"));

                }
                int l = L(now);
                if(!visited[l]) {
                    q.add(new Phase(l, now.str + "L"));

                }
                int r = R(now);
                if(!visited[r]) {
                    q.add(new Phase(r, now.str + "R"));

                }
            }
        }
        bw.flush();bw.close();
    }
}