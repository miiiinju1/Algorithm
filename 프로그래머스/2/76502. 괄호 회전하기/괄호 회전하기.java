import java.util.*;
class Solution {
    static boolean Check(char[] chars) {
        Stack<Character> stack = new Stack();
        int c = 0;
        // System.out.println("start");
        for(int i= 0;i<chars.length;i++) {
            if(!stack.isEmpty()&&stack.peek()==chars[i])  {
                stack.pop();
                c++;
                continue;
            }
            else if(stack.isEmpty()&&(chars[i] == ')'||chars[i]==']'||chars[i]=='}')) {
                    // System.out.println("Cant");
                    return false;
            }
            else if(chars[i]=='(') {
                stack.add(')');
            }
            else if (chars[i] == '[') {
                stack.add(']');
                
            }
            else if (chars[i] == '{') {
                stack.add('}');
            }
            // System.out.print(i+" "+chars[i]+" : ");
            // for(char a : stack) {
            //     System.out.print(a+" ");
            // }
            // System.out.println();
            
        }
        if(stack.size()==0) {
            return true;
        }
        return false;
    }
    public int solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        
        int count = 0;
        for(int i = 0;i<s.length();i++) {
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
            if(Check(sb.toString().toCharArray())) {
                count++;
            }
            
        }
        
        int answer = count;
        return answer;
    }
}
