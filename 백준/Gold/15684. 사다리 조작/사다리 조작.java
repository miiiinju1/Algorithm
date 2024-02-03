import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] limit;

    static class Node {
        int a,b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (a != node.a) return false;
            return b == node.b;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }
    }


    static boolean check() {
        int[][] temp = new int[H+1][N+1];

        for(int h = 1;h<=H;h++) {

            for(int i = 1;i<=N;i++) {
                temp[h][i] = ladder[h][i];
            }
        }

        for(Node p : pick) {
            temp[p.a][p.b] = p.b+1;
            temp[p.a][p.b+1] = p.b;
        }

        for(int i = 1;i<=N;i++) {
            int location = i;
            for(int h=1;h<=H;h++) {
                location = temp[h][location];
            }
            if(location!=i) {
                return false;
            }
        }
        return true;

    }

    static Node[] pick;
    static boolean combination(int index, int now, int count) {
        if(now == 0) {
            pick = new Node[count];
        }

        if(now==count) {
            return check();
        }
        for(int i = index;i<nodes.size();i++) {
            pick[now] = nodes.get(i);
            if(combination(i+1,now+1,count)){
                return true;
            }
        }

        return false;
    }
    static int[][] ladder;


    static int N,M,H;
    static ArrayList<Node> nodes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
         H = Integer.parseInt(st.nextToken());

        ladder = new int[H+1][N+1];
        for(int h= 1;h<=H;h++) {
            for(int i = 1;i<=N;i++) {

                ladder[h][i] = i;
            }
        }
        limit = new int[N+1];
        Arrays.fill(limit,H);

        for(int h = 1;h<=H;h++) {
            for(int i = 1;i<N;i++) {
                nodes.add(new Node(h, i));
            }
        }
        for(int i= 0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes.remove(new Node(a, b));
            nodes.remove(new Node(a, b + 1));
            nodes.remove(new Node(a, b - 1));
            if(b+1<=N) {
                ladder[a][b] = b + 1;
                ladder[a][b + 1] = b;
            }
        }

        pick = new Node[0];
        if(check()) {
            System.out.println(0);
            return ;
        }

        for(int i = 1;i<=3;i++) {
            if(combination(0, 0, i)) {
                System.out.println(i);
                return ;
            }
        }

        System.out.println(-1);



    }
}

//1 2 3 4 5
//3 2 5 1 4