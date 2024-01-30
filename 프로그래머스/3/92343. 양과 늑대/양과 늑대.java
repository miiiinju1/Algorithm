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
        max = Math.max(sheep, max);
        
        // System.out.println(canGo+" "+sheep+" "+wolf);
        
        
        if(sheep>wolf) {
            
            for (int i = 0; i < canGo.size(); i++) {
                ArrayList<Integer> next = new ArrayList<>(canGo);
                next.remove(i);
                for (int j : map.get(canGo.get(i))) {
                    next.add(j);
                }
                dfs(canGo.get(i), sheep, wolf, next);
             }
               
            
        }
        else {
            return ;
        }
        
        
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
    
        try{
            canGo.addAll(map.get(0));
            dfs(0,0,0,canGo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(max);
        
        
        
        
        
        
        
        return max;
    }
}
























