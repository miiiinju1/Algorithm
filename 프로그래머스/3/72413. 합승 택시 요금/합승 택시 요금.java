import java.util.*;
class Solution {
    static HashMap<Integer,ArrayList<Node>> map = new HashMap<>();
    static int[][] shortest;
    static class Node {
        int nextNode;
        
        int price;
        
        public Node (int nextNode,int price ) {
            
            this.nextNode = nextNode;
            this.price = price;
        }
        
        public String toString() {
            return "["+nextNode+" "+price+"]";
        }
        
        
    }
    
    static void putFare(int[] fare) {
        
        if(map.containsKey(fare[0])) {
            map.get(fare[0]).add(new Node(fare[1],fare[2]));
            
        }        
        if(map.containsKey(fare[1])) {
            map.get(fare[1]).add(new Node(fare[0],fare[2]));
            
        }
    }
    
    static int result = Integer.MAX_VALUE;
    
    
    static void dijkstra(int start) {
    
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.price - b.price);
    
        Arrays.fill(shortest[start], Integer.MAX_VALUE);
    
        shortest[start][start] = 0;
    
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.nextNode;
            int price = current.price;

            if (shortest[start][now] < price) continue;

            for (Node neighbor : map.get(now)) {
                if (shortest[start][neighbor.nextNode] > price + neighbor.price) {
                    shortest[start][neighbor.nextNode] = price + neighbor.price;
                    pq.add(new Node(neighbor.nextNode, shortest[start][neighbor.nextNode]));
                }
            }
        }
}

static void calculateAllShortestPaths(int n) {
    for (int i = 1; i <= n; i++) {
        dijkstra(i);
    }
} 
    public int solution(int n, int s, int a, int b, int[][] fares) {
        shortest = new int[n+1][n+1];
        for(int i= 1;i<=n;i++)
            Arrays.fill(shortest[i],Integer.MAX_VALUE);
        
        for(int i= 1;i<=n;i++) {
            map.put(i,new ArrayList<>());
        }
        for(int[] fare : fares) {
            putFare(fare);
        }
        calculateAllShortestPaths(n);

        for(int i= 1;i<=n;i++) {
            
            int sum = shortest[s][i];
            sum+=shortest[i][a];
            sum+=shortest[i][b];
            if (result>sum) 
                result = sum;
        }
          
        return result;
    }
}