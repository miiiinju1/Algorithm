import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge>{
        int next, value;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Edge{");
            sb.append("next=").append(next);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }

        public Edge(int next, int value) {
            this.next = next;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.value,o.value);
        }
    }
    static long max = 0;
    static int maxNode = -1;
    static void search(int now, long value) {
        if(value!=0&&leafCount.get(now)<=1) {
            if(value>max) {
                max = value;
                maxNode = now;
            }
            return ;
        }
        for (Edge edge : map.get(now)) {
            if(!visited[edge.next]) {
                visited[edge.next] = true;
                search(edge.next, value + edge.value);
            }
        }
    }

    static boolean[] visited;
    static HashMap<Integer, Integer> leafCount = new HashMap<>();
    static HashMap<Integer, PriorityQueue<Edge>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        for (int v = 1; v <= V; v++) {
            map.put(v, new PriorityQueue<>(Collections.reverseOrder()));
            leafCount.put(v, 0);
        }

        for(int v = 1;v<=V;v++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while(true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) {
                    break;
                }
                leafCount.replace(next, leafCount.get(next) + 1);

                int weight = Integer.parseInt(st.nextToken());
                map.get(s).add(new Edge(next, weight));
            }
        }


        PriorityQueue<Edge> q = new PriorityQueue<>();
        for (int v = 1; v <= V; v++) {
            if (leafCount.get(v)<= 1) {
                visited = new boolean[V + 1];

                visited[v] = true;
                search(v, 0);
                break;

            }
        }
        visited = new boolean[V + 1];

        visited[maxNode] = true;
        search(maxNode, 0);
        System.out.println(max);



    }

}

//  (4)  (6)
// 2 - 4 - 5
//   /(3)
// 3  - 1
//   (2)



