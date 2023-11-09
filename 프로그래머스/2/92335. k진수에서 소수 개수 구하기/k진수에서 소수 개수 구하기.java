import java.util.*;
class Solution {
    static HashSet<Integer> set = new HashSet<>();
    static int maxIndex = 2;
    public boolean 소수 (String str) {
        long number = Long.parseLong(str);
        if(number==1)
            return false;
        
        long sqrtNumber = (long)Math.sqrt(number)+1;
        
        int i=maxIndex;
        for(;i<sqrtNumber;i++) {
            if(set.contains(i)) {
                
                maxIndex =i;
                return false;
            }
            
            if(number%i==0) {
                set.add(i);
                maxIndex =i;
                return false;
            }
        }
        maxIndex =i;
        return true;
        
        
    }
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while(true) {
            int 나머지 = n%k;
            sb.insert(0,나머지); 
            n = n/k;
            if(n==0) {      
                break;
            }
        }
        // System.out.println(sb);
        String[] strArray = sb.toString().split("0");
        
        int answer=0;
        for(String str : strArray) {
            if(!str.equals("")&&소수(str)) {
                answer++;
            }
        }
        
        return answer;
    }
}