class Solution {
    static boolean isCorrect (String str) {
        int left = 0,right = 0;
        for(int i =0;i<str.length();i++) {
            if(str.charAt(i)=='(') {
                    left++;
                }
                else {
                    right++;
                }
                if(left<right)
                    return false;
                else if(left == right)
                    return true;

            }
            return false;
    }
        
    
    static void change (String str) {
        
        
    }
    static StringBuilder sb =new StringBuilder();
    
    static int split(String str) {
        int left=0,right = 0;
        for(int i =0;i<str.length();i++) {
            if(str.charAt(i)=='(') {
                left++;
            }
            else {
                right++;
            }
            
            if(left==right)
                return i+1;
        }
        return -1;
    }
    
    static String execute(String p) {
        StringBuilder temp = new StringBuilder();
        int splitPoint = split(p);
        if(splitPoint==-1) {
            return "";
        }
        String u = p.substring(0,splitPoint);
        String v = p.substring(splitPoint);
       if(isCorrect(u)) {
           sb.append(u);
           sb.append(execute(v));
       }
       else {
           sb.append("(");
           
           sb.append(execute(v));
           
           sb.append(")");
           String deleted = u.substring(1,u.length()-1);
           for(int i= 1;i<u.length()-1;i++) {
               if(u.charAt(i)=='(') {
                   sb.append(")");
               }
               else {
                   sb.append("(");
               }
           }
       } 
        
        return temp.toString();
    }
    
    
    public String solution(String p) {
        
        execute(p);
        return sb.toString();
    }
}