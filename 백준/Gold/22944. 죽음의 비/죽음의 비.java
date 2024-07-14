
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int y,x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Edge {
        int to, cost;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Edge{");
            sb.append("to=").append(to);
            sb.append(", cost=").append(cost);
            sb.append('}');
            return sb.toString();
        }

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int DEST = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());


        char[][] map = new char[N][N];

        Point start = null;
        Point end = null;

        List<Point> umbrella = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            var input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);

                switch(map[i][j]) {
                    case '.':
                        continue;
                    case 'U':
                        umbrella.add(new Point(i, j));
                        break;
                    case 'S':
                        start = new Point(i, j);
                        break;
                    default:
                        end = new Point(i, j);
                }
            }
        }

        if (Math.abs(start.y - end.y) + Math.abs(start.x - end.x) <= H) {
            System.out.println((Math.abs(start.y - end.y) + Math.abs(start.x - end.x)));
            return;
        }


        Map<Integer, List<Edge>> graph = new HashMap<>();

        DEST = umbrella.size();
        for(int i = 0;i<umbrella.size();i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < umbrella.size(); i++) {
            final Point u1 = umbrella.get(i);

            for (int j = i + 1; j < umbrella.size(); j++) {
                final Point u2 = umbrella.get(j);
                int distance = Math.abs(u1.y - u2.y) + Math.abs(u1.x - u2.x);

                
                if (distance <= H + D) {
                    graph.get(i).add(new Edge(j, distance));
                    graph.get(j).add(new Edge(i, distance));
                }
            }
            final int endD = Math.abs(u1.y - end.y) + Math.abs(u1.x - end.x);
            if (endD <= H + D) {
                graph.get(i).add(new Edge(DEST, endD));
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[][] distances = new int[umbrella.size() + 1][H + 1];
        for(int i= 0;i<=umbrella.size();i++)
            Arrays.fill(distances[i], Integer.MAX_VALUE);

        for (int i = 0; i < umbrella.size(); i++) {
            final Point u1 = umbrella.get(i);
            int distance = Math.abs(u1.y - start.y) + Math.abs(u1.x - start.x);
            if (distance <= H) {
                pq.add(new Node(i, distance, H - distance + 1));
                distances[i][H - distance + 1] = distance;
            }
        }

        while(!pq.isEmpty()) {
            final Node now = pq.poll();

//            System.out.println("now.now+\" \"+now.cost+\" \"+now.health = " + now.now + " " + now.cost + " " + now.health);
            if(now.now==DEST) {
                System.out.println(now.cost);
                return;
            }

            for (Edge edge : graph.get(now.now)) {
//                int health = now.health + (D-2) - (edge.cost-1) ;

                int health = now.health;
                int next = (D - 1) - (edge.cost - 1);
                if (next < 0) {
                    health += next;
                }
                // 현재 체력 + 우산 = > 거리 - 우산 edge.cost - (D-1)
                if(health<=0) continue;
//                else if(health >= H) {
//                    health = H-1;
//                }

                if(distances[edge.to][health] > now.cost + edge.cost) {
                    distances[edge.to][health] = now.cost + edge.cost;
                    pq.add(new Node(edge.to, now.cost + edge.cost, health));
                }
            }
        }

        System.out.println(-1);




    }
    static class Node implements Comparable<Node> {
        int now, cost;
        int health;

        public Node(int now, int cost, int health) {
            this.now = now;
            this.cost = cost;
            this.health = health;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}

//6 2 5
//......
//......
//E..S.U
//......
//......
//......