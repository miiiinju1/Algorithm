import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int next, cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }


    }

    static int search(int now, int cost, int before) {

        final List<Node> nodes = map.get(now);

        if(nodes.size()==1 && now!=1) {
            return cost;
        }

        int temp = 0;
        for (Node node : nodes) {
            if (node.next==before) {
                continue;
            }
            temp+= search(node.next, node.cost, now);
        }

        return Math.min(temp, cost);
    }

    static HashMap<Integer, List<Node>> map;// = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            map = new HashMap<>();
            for(int n = 1;n<=N;n++) {
                map.put(n, new ArrayList<>());
            }
            for(int m = 0;m<M;m++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int D = Integer.parseInt(st.nextToken());

                map.get(A).add(new Node(B, D));
                map.get(B).add(new Node(A, D));
            }

            final int search = search(1, Integer.MAX_VALUE, 0);
            bw.write(search+"\n");

        }
        bw.flush();bw.close();
    }
}
