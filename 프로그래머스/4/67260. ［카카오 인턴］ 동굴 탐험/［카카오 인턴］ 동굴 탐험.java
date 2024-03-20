import java.util.*;

class Solution {
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static HashSet<Integer> hasKey = new HashSet<>();
    static HashMap<Integer, Integer> key = new HashMap<>();
    static HashSet<Integer> staging = new HashSet<>();
    static HashSet<Integer> require = new HashSet<>();
    public boolean solution(int n, int[][] paths, int[][] orders) {
        for(int i= 0;i<n;i++) {
            map.put(i, new ArrayList<>());
        }
        
        for(int[] path:paths) {
            map.get(path[0]).add(path[1]);
            map.get(path[1]).add(path[0]);
        }

        for(int[] order: orders) {
            key.put(order[0], order[1]);
            require.add(order[1]);
        }
        
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int count = 1;
      
        if(require.contains(0)) return false;
        
        while(!q.isEmpty() || !staging.isEmpty()) {
            Integer now = -1;
            if(!q.isEmpty())
                now = q.poll();
//             else if(!staging.isEmpty() && !hasKey.isEmpty()) {
//                 for(Integer s : staging) {
//                     if(hasKey.contains(s)) {
//                         q.add(s);
//                         hasKey.remove(s);
//                     }
//                 }
                
//                 if(q.isEmpty()) { 
                    
//                     break;
//                 }
//                 continue;
//             }    
         
            if(key.containsKey(now)) {
               hasKey.add(key.get(now));
                if(staging.contains(key.get(now))) {
                    q.add(key.get(now));
                    staging.remove(key.get(now));
                }
            }
            if(now==-1) {
                break;
            }
            // if(staging.contains(now)) {
            //     staging.remove(now);
            // }
           for(Integer next : map.get(now)) {
               if(!visited[next]) {
                   count++;
                   visited[next] = true;
                    
                    //키가 필요할 떄
                   if(require.contains(next)) {
                       //키 있으면
                       if(hasKey.contains(next)) {
                           hasKey.remove(next);
                           q.add(next);
                       }
                       //키 없으면
                       else {
                           staging.add(next);
                       }
                   }
                   else {
                       q.add(next);
                   }
               }
               
               
           }
        }
    
        return count==n;
    }
}