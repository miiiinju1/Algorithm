
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        HashMap<String, Integer> set1 = new HashMap<>();
        HashMap<String, Integer> set2 = new HashMap<>();
        
        boolean before = false;
        
        if(str1.charAt(0)-'A'>=0 && str1.charAt(0)-'A'<26) {
            before = true;
        }
        for(int i = 1;i<str1.length();i++) {
            if(str1.charAt(i)-'A'>=0 && str1.charAt(i)-'A'<26) {
                if(before) {
                    String input = str1.substring(i-1,i+1);
                    
                    set1.put(input, set1.getOrDefault(input, 0)+1);
                }    
                
                before = true;
                
            }
            else {
                before = false;
            }
        }
        before = false;
        
        if(str2.charAt(0)-'A'>=0 && str2.charAt(0)-'A'<26) {
            before = true;
        }
        for(int i = 1;i<str2.length();i++) {
            if(str2.charAt(i)-'A'>=0 && str2.charAt(i)-'A'<26) {
                if(before) {
                    String input = str2.substring(i-1,i+1);
                    
                    set2.put(input, set2.getOrDefault(input,0)+1);
                }    
                before = true;
            }
            else {
                before = false;
            }
        }
        // ArrayList<String> temp = new ArrayList<>(set1);
        // temp.addAll(set2);
        System.out.println(set1);
        System.out.println(set2);
        
        
        int retain = 0;
        
        for(Map.Entry<String, Integer> entry : set1.entrySet()) {
            String key = entry.getKey();
            if(set2.containsKey(key)) {
                retain+=(Math.min(set2.get(key), entry.getValue()));
            }
        }
        
        for(Map.Entry<String, Integer> entry : set2.entrySet()) {
            String key = entry.getKey();
            if(set1.containsKey(key)) {
                set1.replace(key, Math.max(entry.getValue(),set1.get(key)));
            }
             else {
                 set1.put(key,entry.getValue());
               
            }
        }
         
        int add = 0;
        for(Map.Entry<String, Integer> entry : set1.entrySet()) {
            add+=entry.getValue();
        }
         
        int answer = 0;
        if(add==0&&retain==0) {
            answer = 65536;
        }
        
        else if(add!=0) {
         answer = (int) (65536*((double)retain/add));
            
        }
        
        return answer;
    }
}