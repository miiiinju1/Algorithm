import java.util.*;
import java.util.regex.*;

class Solution {
    
    static ArrayList<String> users = new ArrayList<>();
    static ArrayList<String> banned = new ArrayList<>();
    
    static boolean[] visited;
    
    static HashSet<String> picked = new HashSet<>();
    static HashSet<HashSet<String>> result = new HashSet<>();
    static void search(int now, HashSet<String> set) {
        
        if(now==banned.size()) {
            result.add(set);
            
            return;
        }
        
        for(int i= 0;i<visited.length;i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(Pattern.matches(banned.get(now), users.get(i))) {
                    HashSet<String> temp = new HashSet<>(set);
                    temp.add(users.get(i));
                    search(now+1, temp);
                }
                else {
                    search(now, set);
                }
                visited[i] = false;
                
            }
            
            
        }
        
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        for(String user: user_id) {
            users.add(user);
        }
        visited = new boolean[user_id.length];
        
        for(int i =0;i<banned_id.length;i++) {
            banned.add("^"+banned_id[i].replaceAll("\\*",".")+"$");
        }
        search(0,new HashSet<>());
        
        return result.size();
    }
}