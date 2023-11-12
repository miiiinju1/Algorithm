import java.util.*;

class Solution {
    static ArrayList<String> operators = new ArrayList<>();
    static boolean[] visited;
    static ArrayList<ArrayList<String>> testCase = new ArrayList<>();
    static ArrayList<String> temp = new ArrayList<>();
    static void permutation(int count) {
        
        if(count == visited.length) {
            testCase.add(new ArrayList<>(temp));
        }
        for (int i= 0;i<operators.size();i++) {
            if(!visited[i]) {
                String now = operators.get(i);
                temp.add(now);
                visited[i] = true;
                permutation(count+1);
                temp.remove(now);
                visited[i]=false;
            }
        }
        
        
    }
    
    static long calculate(ArrayList<String> 수식, int index) {
        ArrayList<String> 우선순위 = testCase.get(index);
        
        for(int i= 0;i<우선순위.size();) {
            
            String 연산자 = 우선순위.get(i);
            int operatorIndex = 수식.indexOf(연산자);    
            if(operatorIndex==-1) {
                i++;
                continue;
            }
             long right = Long.parseLong(수식.remove(operatorIndex+1));
            수식.remove(operatorIndex);
            long left = Long.parseLong(수식.remove(operatorIndex-1));
            
            if(연산자.equals("*")) {
                수식.add(operatorIndex-1,String.valueOf(left*right));
            }
        
            else if (연산자.equals("+")) {
                수식.add(operatorIndex-1,String.valueOf(left+right));
            }
            else {
                수식.add(operatorIndex-1,String.valueOf(left-right));
            }
            
        }
        
        return Long.parseLong(수식.get(0));
        
    }
    
    public long solution(String expression) {
        
        if(expression.contains("*"))
            operators.add("*");
       if(expression.contains("+"))
        operators.add("+");
       if(expression.contains("-"))
        operators.add("-");
        visited = new boolean[operators.size()];
        permutation(0);
        //수식 분할
        ArrayList<String> 수식 = new ArrayList<>();    
        StringBuilder sb= new StringBuilder();
        for(int i= 0;i<expression.length();i++) {
            
            char now = expression.charAt(i);
            
            if(now=='*'||now=='+'||now=='-')
               {
                   수식.add(sb.toString());
                   sb = new StringBuilder();
                   수식.add(String.format("%c",now));
               }
            else {
                sb.append(now);
            }
               
              
        }
        수식.add(sb.toString());
        
        //분할 완료
        long max = 0L;
        for(int i= 0;i<testCase.size();i++) {
            
            Long result = calculate(new ArrayList<String>(수식), i);
            
            if(result<0)
                result*=-1;
            if(max<result) {
                max = result;
            }
            
            
        }
        
        long answer = max;
        return answer;
    }
}
