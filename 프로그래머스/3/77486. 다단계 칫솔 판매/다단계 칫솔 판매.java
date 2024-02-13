import java.util.*;
class Solution {
    
    static HashMap<String, String> map = new HashMap<>();
    
    static void search(String now, int price) {
        if(now.equals("-"))
            return ;
        
        if((int)(price*0.1)>=1) {
            int ret = (int)(price*0.1);
            result.put(now,result.getOrDefault(now,0) + price-ret);
            
            if(map.containsKey(now))
                search(map.get(now),ret);
        }
        else {
            result.put(now,result.getOrDefault(now,0)+price);
        }
    }
    static HashMap<String, Integer> result = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] sellers, int[] amount) {
        
        for(int i= 0;i<enroll.length;i++) {
            map.put(enroll[i],referral[i]);
        }
        
        for(int i= 0;i<sellers.length;i++) { 
            search(sellers[i],amount[i]*100);
        }
        return Arrays.stream(enroll)
            .map(en -> result.getOrDefault(en,0))
            .mapToInt(Integer::intValue)
            .toArray();
    }
}