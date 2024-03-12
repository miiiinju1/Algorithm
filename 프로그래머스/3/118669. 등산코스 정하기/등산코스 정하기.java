import java.util.*;
class Solution {
    static class Node implements Comparable<Node> {
        int next, weight;
        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight,o.weight);
        }
        public String toString() {
            return next+" "+weight;
        }
    }
    
    static class Point implements Comparable<Point> {
        int next, weight, minWeight;
        public Point(int next, int weight, int minWeight) {
            this.next = next;
            this.weight = weight;
            this.minWeight = minWeight;
        }
        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
    static HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();
    static HashSet<Integer> summit = new HashSet<>();
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        
        for(int s : summits) {
            summit.add(s);
        }
        
        for(int[] path: paths) {
            int a = path[0];
            int b = path[1];
            int w = path[2];
            
            map.computeIfAbsent(a, k-> new PriorityQueue<>());
            map.computeIfAbsent(b, k-> new PriorityQueue<>());
            map.get(a).add(new Node(b,w));
            map.get(b).add(new Node(a,w));
            
        }
        
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[] visited = new int[n+1];
       
        Arrays.fill(visited,100000000);
        for(int g : gates) {
            pq.add(new Point(g,0,0));
            visited[g] = 0;
        }
        
        // System.out.println(map);
        // System.out.println(summit);
        
        int min = Integer.MAX_VALUE;
        int dest = -1;

        
        PriorityQueue<Integer> result = new PriorityQueue<>( );
        while(!pq.isEmpty()) {
            Point now = pq.poll();
            
        
            
             if(min<now.minWeight) {
                break;
            }
            
            if(summit.contains(now.next) && min>=now.minWeight) {
                min = now.minWeight;
                // result.add(new Node(now.next,now.minWeight));
                result.add(now.next);
                continue;
            }
          
            for(Node node : map.get(now.next)) {
                if(visited[node.next]>node.weight) {
                    visited[node.next] = node.weight;
                    pq.add(new Point(node.next, node.weight, Math.max(now.minWeight, node.weight)));
                }
            } 
        }
        
        int[] answer = {result.peek(),min};
        return answer;
    }
}