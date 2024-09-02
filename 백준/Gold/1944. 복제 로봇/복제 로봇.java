
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static class Edge {

        int to, cost;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Edge{");
            sb.append("to=").append(to);
            sb.append(", cost=").append(cost);
            sb.append("}\n");
            return sb.toString();
        }

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N;
    
    static class UFEdge implements Comparable<UFEdge>{
        int from, to,cost;

        public UFEdge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(UFEdge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static Map<Integer, List<Edge>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        
        char[][] charMap = new char[N][N];

        int nodeCount = 0;
        int start = -1;
        int[][] nodeMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                charMap[i][j] = input.charAt(j);
                if (charMap[i][j] == 'S') {
                    nodeMap[i][j] = ++nodeCount;
                    start = nodeCount;
                }
                else if (charMap[i][j] == 'K') {
                    nodeMap[i][j] = ++nodeCount;
                }
            }
        }
        for (int i = 1; i <= nodeCount; i++) {
            map.put(i, new ArrayList<>());
        } 
        int[][] visited = new int[N][N];

        ArrayList<UFEdge> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (charMap[i][j] == 'S' || charMap[i][j] == 'K') {
                    for(int t= 0;t<N;t++) {
                        Arrays.fill(visited[t], Integer.MAX_VALUE);
                    }
                    
                    visited[i][j] = 0;
                    Deque<Point> q = new ArrayDeque<>();
                    q.add(new Point(i, j));

                    int from = nodeMap[i][j];
                    while(!q.isEmpty()) {
                        Point now = q.poll();

                        if (!(now.y == i && now.x == j)) {
                            if (charMap[now.y][now.x] == 'S' || charMap[now.y][now.x] == 'K') {
                                int target = nodeMap[now.y][now.x];
                                int cost = visited[now.y][now.x];
                                list.add(new UFEdge(from, target, cost));
                            }
                        }

                        for (int d = 0; d < 4; d++) {
                            int y = dy[d] + now.y;
                            int x = dx[d] + now.x;

                            if (isValid(y, x) && charMap[y][x] != '1'
                                && visited[y][x] > visited[now.y][now.x] + 1) {
                                visited[y][x] = visited[now.y][now.x] + 1;
                                q.add(new Point(y, x));
                            }
                        }
                    }

                }
            }
        }
        Collections.sort(list);
        parent = new int[nodeCount + 1];
        for(int i =1;i<=nodeCount;i++) {
            parent[i] = i;
        }
        for (UFEdge ufEdge : list) {
            if (union(ufEdge.from, ufEdge.to)) {

                map.get(ufEdge.from).add(new Edge(ufEdge.to, ufEdge.cost));
                map.get(ufEdge.to).add(new Edge(ufEdge.from, ufEdge.cost));
            }

        }


        //여기에서부터 각 노드별로 트리만들어보기
        treeVisit = new boolean[nodeCount + 1];
        treeVisit[start] = true;
        int result = dfs(start);

        for(int i =1 ;i<=nodeCount;i++) {
            if(!treeVisit[i]) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(result);


    }
    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if(fa!=fb) {
            parent[fa] = fb;
            return true;
        }
        return false;
    }
    static int[] parent;

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    static int dfs(int now) {
        int temp = 0;
        for (Edge edge : map.get(now)) {
            if (!treeVisit[edge.to]) {
                treeVisit[edge.to] = true;
                temp += edge.cost;
                temp += dfs(edge.to);
            }
        }
        return temp;
    }
    static boolean[] treeVisit;

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

}