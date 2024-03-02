import java.util.*;
class Solution {
    static HashMap<String,Integer> visited = new HashMap<>();
    static ArrayList<String> result = new ArrayList<>();
    static boolean search(String now) {
        if(0==visited.size()) {
            return true;
        }
        for(String next : map.get(now)) {
            if(visited.containsKey(now+next)) {
                
                visited.replace(now+next,visited.get(now+next)-1);
                if(visited.get(now+next)==0) {
                    visited.remove(now+next);
                }
                
                if(search(next)) {
                    result.add(next);
                    
                    return true;
                }
                visited.put(now+next, visited.getOrDefault(now+next, 0)+1);

            }
        }
        return false;
    }
    static HashMap<String, ArrayList<String>> map = new HashMap<>(); 
    public String[] solution(String[][] tickets) {
        for(String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            
            if(!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            if(!map.containsKey(to)) {
                map.put(to, new ArrayList<>());
            }
            visited.put(from+to, visited.getOrDefault(from+to, 0)+1);
            map.get(from).add(to);
        }
        
        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            
            Collections.sort(entry.getValue());
        
        }
        search("ICN");
        result.add("ICN");
        Collections.reverse(result);
        
        return result.stream().toArray(String[]::new);
    }
}