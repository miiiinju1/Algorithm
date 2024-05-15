import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Ret {
        NodeValue adj;

        public Ret(NodeValue adj, NodeValue nonAdj) {
            this.adj = adj;
            this.nonAdj = nonAdj;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Ret{");
            sb.append("adj=").append(adj);
            sb.append(", nonAdj=").append(nonAdj);
            sb.append('}');
            return sb.toString();
        }

        NodeValue nonAdj;
        
    }
    static class NodeValue {
        int value;
        List<Integer> nodes;

        public NodeValue(int value, List<Integer> nodes) {
            this.value = value;
            this.nodes = nodes;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("NodeValue{");
            sb.append("value=").append(value);
            sb.append(", nodes=").append(nodes);
            sb.append('}');
            return sb.toString();
        }
    }

    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;


    static Ret dfs(int now) {

        //날 안 먹고 바로 밑에 거랑 더할거
        int adjValue = 0;
        List<Integer> adjList = new ArrayList<>();
        
        //날 먹고 그 밑에 있는거
        int nonValue = node[now];
        List<Integer> nonList = new ArrayList<>();
        nonList.add(now);

        if(!map.containsKey(now)) {
            return new Ret(new NodeValue(adjValue, adjList), new NodeValue(nonValue, nonList));
        }

        
        for (Integer next : map.get(now)) {
            if (!visited[next]) {
                visited[next] = true;

                final Ret result = dfs(next);
//                System.out.println(now+" "+"result = " + result);

                //날 먹고 떨어진 애랑
                nonValue += result.adj.value;
                nonList.addAll(result.adj.nodes);

                // 날 안 먹고  바로 밑에 애랑 더할거
                if (result.nonAdj.value > result.adj.value) {
                    adjValue += result.nonAdj.value;
                    adjList.addAll(result.nonAdj.nodes);
                }
                else {
                    adjValue += result.adj.value;
                    adjList.addAll(result.adj.nodes);
                }
                
            }
        }
//        System.out.println("adjValue = " + adjValue);
//        System.out.println("nonValue = " + nonValue);
        return new Ret(new NodeValue(adjValue, adjList), new NodeValue(nonValue, nonList));


    }

    static int[] node;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
         node = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =1;i<=N;i++) {
            node[i] = Integer.parseInt(st.nextToken());
        }

        for(int i= 1;i<N;i++) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch(Exception e) {
                break;
            }

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(a, v -> new ArrayList<>());
            map.computeIfAbsent(b, v -> new ArrayList<>());

            map.get(a).add(b);
            map.get(b).add(a);

        }
        visited[1] = true;
        final Ret dfs = dfs(1);

        StringBuilder sb = new StringBuilder();
        if(dfs.adj.value> dfs.nonAdj.value) {
            System.out.println(dfs.adj.value);
            Collections.sort(dfs.adj.nodes);
            for (Integer i : dfs.adj.nodes) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
        }
        else {
            System.out.println(dfs.nonAdj.value);
            Collections.sort(dfs.nonAdj.nodes);
            
            for (Integer i : dfs.nonAdj.nodes) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
        }
//        System.out.println("dfs = " + dfs);

    }
}
