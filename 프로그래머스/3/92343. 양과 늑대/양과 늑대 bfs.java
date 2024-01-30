import java.util.*;

class Solution {
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static class Phase {
        int now, sheep, wolf;
    
        ArrayList<Integer> children;
        
        public Phase(int now, int sheep, int wolf, ArrayList<Integer> children) {
            this.now = now;
            this.sheep = sheep;
            this.wolf = wolf;
            this.children = new ArrayList<>(children);
            
        }
    }
    //BFS풀이
    static int bfs() {
        int max = 0;
        ArrayDeque<Phase> q = new ArrayDeque<>();
        ArrayList<Integer> initialList = new ArrayList<>(map.get(0));
        
        q.add(new Phase(0,0,0,initialList));
        
        while(!q.isEmpty()) {
            Phase now = q.poll();
           
            if(info[now.now]==0) {
                now.sheep++;
            }
            else {
                now.wolf++;
            }
             max = Math.max(now.sheep, max);
            if(now.sheep<=now.wolf) {
                continue;
            }
            for(Integer child : now.children) {
                Phase next = new Phase(child,now.sheep,now.wolf,now.children);
                next.children.remove(child);
                next.children.addAll(map.get(child));
                q.add(next);
            }
        }
        return max;
    }
    static int[] info;
    public int solution(int[] inf, int[][] edges) {
        info = inf;
        for(int i= 0;i<info.length;i++) {
            map.put(i,new ArrayList<Integer>());
        }
        for(int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
        }
        return bfs();
    }
}
























