import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int next;
        long weight;
        public Node(int next, long weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static HashMap<Integer, ArrayList<Node>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        for(int i = 1;i<=V;i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i = 0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map.get(u).add(new Node(v, w));
            map.get(v).add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        Collection<Integer> macdonalds = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<M;i++) {
            macdonalds.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        Collection<Integer> starbucks = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<S;i++) {
            starbucks.add(Integer.parseInt(st.nextToken()));
        }

        //macdonalds = 0;
        //starbucks = 1;
        long[][] visited = new long[2][V+1];
        for(int z =0;z<2;z++) {
            Arrays.fill(visited[z], Integer.MAX_VALUE);
        }
        long min = Long.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Integer i : macdonalds) {

            pq.add(new Node(i, 0));
            visited[0][i] = 0;
        }
            //맥도날드 찾기
            while (!pq.isEmpty()) {

                final Node node = pq.poll();

                for (Node child : map.get(node.next)) {
                    if (visited[0][child.next] > node.weight + child.weight) {
                        if(node.weight+child.weight>x) {
                            continue;
                        }
                        visited[0][child.next] = node.weight + child.weight;
                        pq.add(new Node(child.next, node.weight + child.weight));
                    }
                }
            }

        pq = new PriorityQueue<>();
        for (Integer i : starbucks) {
            pq.add(new Node(i, 0));
            visited[1][i] = 0;
        }
            //스타벅스 찾기
            while(!pq.isEmpty()) {

                final Node node = pq.poll();

                for (Node child : map.get(node.next)) {
                    if(visited[1][child.next] > node.weight + child.weight) {
                        if(node.weight+child.weight>y) {
                            continue;
                        }
                        visited[1][child.next] = node.weight + child.weight;
                        pq.add(new Node(child.next, node.weight + child.weight));
                    }
                }
            }


        for(int z = 1;z<=V;z++) {
            
            if(macdonalds.contains(z) || starbucks.contains(z)) continue;
            if(visited[0][z] ==Integer.MAX_VALUE || visited[1][z]==Integer.MAX_VALUE)
                continue;
            long sum = visited[0][z] + visited[1][z];

            min = Math.min(sum,min);
        }
if(min==Long.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);

    }
}
