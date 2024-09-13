import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static Map<Integer, PriorityQueue<Edge>> map;

    static class Edge implements Comparable<Edge> {
        int to, distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            map = new HashMap<>();
            var st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            for(int i = 1;i<=n;i++) {
                map.put(i, new PriorityQueue<>());
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                map.get(a).add(new Edge(b, d));
                map.get(b).add(new Edge(a, d));
            }

            List<Integer> targets = new ArrayList<>();
            for(int i = 0;i<t;i++) {
                targets.add(Integer.parseInt(br.readLine()));
            }

            int[][] distanceS = new int[4][n + 1];
            int INIT = Integer.MAX_VALUE / 2;
            for(int i = 0;i<4;i++)
                Arrays.fill(distanceS[i], INIT);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            int bit = 0;
            if(s==g) {
                bit |= 2;
            } else if(s==h) {
                bit |= 1;
            }
            pq.add(new Node(s, 0,bit));
            distanceS[0][s] = 0;
            while(!pq.isEmpty()) {
                Node node = pq.poll();

                for (Edge edge : map.get(node.now)) {
                    int nextBit = node.bit;
                    if(edge.to == g) {
                        nextBit |= 2;
                    } else if(edge.to == h) {
                        nextBit |= 1;
                    }
                    if (distanceS[nextBit][edge.to] > node.value + edge.distance) {
                        distanceS[nextBit][edge.to] = node.value + edge.distance;
                        pq.add(new Node(edge.to, distanceS[nextBit][edge.to], nextBit));
                    }
                }
            }
//            for(int i= 0;i<4;i++) {
//                System.out.println(Arrays.toString(distanceS[i]));
//            }
            Collections.sort(targets);
            a: for (Integer target : targets) {
                if(distanceS[3][target]== INIT) continue;


                int should = distanceS[3][target];
                for(int i = 0;i<3;i++) {
                    if(distanceS[i][target] < should) continue a;
                }
                bw.write(target+" ");
            }

            bw.newLine();


        }
        bw.flush();bw.close();
    }

    static class Node implements Comparable<Node>  {
        int now, value;
        int bit;// bit 00, 01 10 11 = 3;

        public Node(int now, int value, int bit) {
            this.now = now;
            this.value = value;
            this.bit = bit;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }

}
