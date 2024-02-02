import java.util.*;
class Solution {
    public int[] solution(String s) {
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));
        
        String[] str = s.split("\\},\\{");
        for(String ss : str) {
            ss = ss.replaceAll("\\{","");
            ss = ss.replaceAll("\\}","");
            pq.add(ss);
        }
        
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        while(!pq.isEmpty()) {
            str = pq.poll().split(",");
            for(String ss : str) {
                int now = Integer.parseInt(ss);
                if(!visited.contains(now)) {
                    list.add(now);
                    visited.add(now);
                }
                
            }
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}