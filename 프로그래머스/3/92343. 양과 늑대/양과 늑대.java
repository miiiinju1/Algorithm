import java.util.*;

class Solution {
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    
    static int max = 0;
    
    static void dfs(int now, int sheep, int wolf, List<Integer> canGo) {
       
        if(info[now]==0) {
            sheep++;
        }
        else {
            wolf++;
        }
        if(sheep<=wolf) {
            return;
        }
        max = Math.max(sheep,max);
        for(Integer next: canGo) {
            ArrayList<Integer> nextList = new ArrayList<Integer> (canGo);
            nextList.addAll(map.get(next));
            nextList.remove(next);
            dfs(next,sheep,wolf,nextList);
        }
        
        
    }
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
        
        ArrayList<Integer> canGo = new ArrayList<>();
        canGo.addAll(map.get(0));
        // dfs(0,0,0,canGo);
      
        bfs();
        System.out.println(max);
        return bfs();
    }
}
























