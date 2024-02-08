import java.util.*;
class Solution {
    public int[] solution(String msg) {

        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i= 0;i<26;i++) {
            map.put(String.valueOf((char)(65+i)), i+1);
        }
        int index = 27;
        StringBuilder sb = new StringBuilder() ;
        
        ArrayList<Integer> sum = new ArrayList<>();
        for(int i= 0 ;i<msg.length();i++) {
            
            sb = new StringBuilder();
            int j= i;
            for(;j<msg.length();j++) {
                sb.append(msg.charAt(j));
                if(!map.containsKey(sb.toString())) {
                    map.put(sb.toString(),index++);
                    sb.deleteCharAt(sb.length()-1);
                    break;
                }
            }
                i = j-1;
                sum.add(map.get(sb.toString()));
        }
       
        
        return sum.stream().mapToInt( i-> i).toArray();
    }
}