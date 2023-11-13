import java.util.*;
class Solution {
    
    static int compress(String str, int 단위) {
        int count = 0;
        String before = str.substring(0,단위);
        int nowCount=1;
        int i = 단위;
        for(;i<str.length()-단위;i+=단위) {
            String now = str.substring(i,i+단위);
            
            if(before.equals(now)) {
                nowCount++;
            }
            else {
                if(nowCount>1) {
                   count+= String.valueOf(nowCount).length();
            }
                count+=(before.length());
                
                before = now;
                nowCount = 1;
            }
        }
        
        String now = str.substring(i,str.length());
        
        if(before.equals(now)) {
            nowCount++;
            if(nowCount>1) {
                  count+= String.valueOf(nowCount).length();
                    
            }
            count+=now.length();
        
        }
        else {
           if(nowCount>1) {
                count+= String.valueOf(nowCount).length();
                    
            }
            count+=(before.length());
            count+=(now.length());
            before = now;
            nowCount = 1;
            
        }
        return count;
    }
    

    public int solution(String s) {
        
        //단위
        int min = 1001;
        
        for(int 단위=1; 단위<s.length()/2+1;단위++) {
            int length = compress(s,단위);
            if(min>length)
                min = length;
          }
        if(s.length()==1)
            min =1;
        
        
        return min;
    }
}



//2a2ba3c

//2ab2cd2ab2cd 12 (2)

//2ababcdcd 9 (8)
