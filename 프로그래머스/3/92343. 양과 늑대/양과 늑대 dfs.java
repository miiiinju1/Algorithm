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
            //그럼 이 때까지는 부모에 살아있으니깐?
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
























