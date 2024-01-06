import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int next, cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static HashMap<Integer, ArrayList<Node>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str;

        boolean flag = false;
        try {
            while ((str = (br.readLine())) != null) {

                st = new StringTokenizer(str);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                if (!map.containsKey(a)) {
                    map.put(a, new ArrayList<>());
                }
                if (!map.containsKey(b)) {
                    map.put(b, new ArrayList<>());
                }

                map.get(a).add(new Node(b, cost));
                map.get(b).add(new Node(a, cost));
                flag = true;

            }
        }
        catch(Exception e) {

        }
        if(!flag) {
            System.out.println(0);
            return ;
        }
        else {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            HashMap<Integer, Integer> visited = new HashMap<>();
            visited.put(1, 0);
            pq.add(new Node(1, 0));
            while (!pq.isEmpty()) {
                final Node now = pq.poll();

                if (!visited.containsKey(now.next)) {
                    visited.put(now.next, now.cost);
                }

                //이거 빠져야하나,,
                else if (visited.get(now.next) < now.cost) {
                    continue;
                }

                for (Node node : map.get(now.next)) {
                    if (visited.get(now.next) < now.cost + node.cost) {
                        pq.add(new Node(node.next, node.cost + now.cost));
                    }
                }

            }

            Map.Entry<Integer, Integer> entry = visited.entrySet().stream().max(Map.Entry.comparingByValue()).get();


            pq = new PriorityQueue<>();
            visited = new HashMap<>();
            visited.put(entry.getKey(), 0);
            pq.add(new Node(entry.getKey(), 0));
            while (!pq.isEmpty()) {
                final Node now = pq.poll();

                if (!visited.containsKey(now.next)) {
                    visited.put(now.next, now.cost);
                }

                //이거 빠져야하나,,
                else if (visited.get(now.next) < now.cost) {
                    continue;
                }

                for (Node node : map.get(now.next)) {
                    if (visited.get(now.next) < now.cost + node.cost) {
                        pq.add(new Node(node.next, node.cost + now.cost));
                    }
                }

            }
            entry = visited.entrySet().stream().max(Map.Entry.comparingByValue()).get();

            System.out.println(entry.getValue());

        }
    }
}
