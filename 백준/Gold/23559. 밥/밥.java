import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node>{
        int big, small;
        int diff;

        public Node(int big, int small) {
            this.big = big;
            this.small = small;
            diff = big-small;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.diff, this.diff);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int money;
        money = N*1000;
        long value = 0;
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            value+=b;
            pq.add(new Node(a, b));
        }

        while(!pq.isEmpty()) {
            final Node now = pq.poll();

            if(now.diff<=0) {
                break;
            }
            if(money+4000<=X) {
                value+=now.diff;
                money+=4000;
            }
            else {
                break;
            }

        }
        System.out.println(value);
    }
}
