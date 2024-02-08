import java.util.*;
class Solution {
    
    public char convert(int i) {
        if(i<10) {
            return (char)(i+'0');
        }
        else {
           return  (char) (65 + i-10);
        }
    }
    
    public StringBuilder transform(int n, int nowNum) {
        StringBuilder sb = new StringBuilder();
        while(true) {
            int 몫 = nowNum/n;
            int 나머지 = nowNum%n;
            sb.insert(0,convert(나머지));
            nowNum = 몫;
            if(nowNum==0) {
                break;
            }
        }
        return sb;
    }
    
    public String solution(int n, int t, int m, int p) {
        
        
        StringBuilder sb = new StringBuilder();
        StringBuilder now = new StringBuilder();
        int target = 0;
        int turn = 1;
        while(true) {
            if(now.length()==0) {
                now = transform(n,target);
                target++; 
            }
            if(turn == p) {
                // System.out.println("turn");
                sb.append(now.charAt(0)); 
                if(sb.length()==t) {
                    break;
                }
            }
            if(turn == m) {
                turn = 0;
            }
            
            turn++;
            now.deleteCharAt(0);
            // System.out.println("sb: "+sb);
           
            
        }
    
        String answer = sb.toString();
        return answer;
    }
}